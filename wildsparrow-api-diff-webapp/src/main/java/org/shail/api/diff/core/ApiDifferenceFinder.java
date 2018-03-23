
package org.shail.api.diff.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.shail.api.diff.report.report.dto.APIDifferenceReportDTO;
import org.shail.api.diff.report.report.dto.APIDifferenceReportDetailsDTO;

public class ApiDifferenceFinder {

	private static final Logger LOG = Logger.getLogger(ApiDifferenceFinder.class.getName());
	// private static final String UTIL_LIB =
	// Configuration.getStringProperty("UTIL_LIB");
	private static final String API_NAMING_PATTERN_END = "";// Configuration.getStringProperty("API_NAMING_PATTERN_END");
	private static final String API_NAMING_PATTERN_START = "";// Configuration.getStringProperty("API_NAMING_PATTERN_START");
	// private static final String REPORT_PATH_FOR_ADDED ="";//
	// Configuration.getStringProperty("REPORT_PATH_FOR_ADDED");
	// private static final String REPORT_PATH_FOR_REMOVED ="";//
	// Configuration.getStringProperty("REPORT_PATH_FOR_REMOVED");
	// private static final String REPORT_PATH_FOR_MODIFIED_SIGNATURE_CHANGE ="";//
	// Configuration.getStringProperty("REPORT_PATH_FOR_MODIFIED_SIGNATURE_CHANGE");
	// private static final String REPORT_PATH_FOR_MODIFIED_INPUT_DTO_FIELD_ADDED =
	// "";//Configuration.getStringProperty("REPORT_PATH_FOR_MODIFIED_INPUT_DTO_FIELD_ADDED");
	// private static final String REPORT_PATH_FOR_MODIFIED_INPUT_DTO_FIELD_REMOVED
	// =
	// "";//Configuration.getStringProperty("REPORT_PATH_FOR_MODIFIED_INPUT_DTO_FIELD_REMOVED");
	// private static final String REPORT_PATH_FOR_AMBIGUOUS =
	// "";//Configuration.getStringProperty("REPORT_PATH_FOR_AMBIGUOUS");
	// private static final String DTO_EXCLUSION_STRG =
	// "";//Configuration.getStringProperty("DTO_EXCLUSION_LIST");
	private static List<String> DTO_EXCLUSION_LIST = new ArrayList<>();// (DTO_EXCLUSION_STRG == null ||
																		// DTO_EXCLUSION_STRG.isEmpty()) ? new
																		// ArrayList<String>() :
																		// Arrays.asList(DTO_EXCLUSION_STRG.split(";"));
	private static final String CLASS_EXCLUSION_LIST = "";// Configuration.getStringProperty("CLASS_EXCLUSION_LIST");
	private static final String FILE_SEPERATOR = "/";
	// private static final ObjectMapper mapper = new ObjectMapper();
	private static URLClassLoader previousVersionClassLoader = null;
	private static Map<String, Map<String, MethodAttributes>> previousVersionClassMethodInfoMap = new HashMap<String, Map<String, MethodAttributes>>();
	private static URLClassLoader currentVersionClassLoader = null;
	private static Map<String, Map<String, MethodAttributes>> currentVersionClassMethodInfoMap = new HashMap<String, Map<String, MethodAttributes>>();

	public ApiDifferenceFinder() {

		// if (UTIL_LIB != null && !UTIL_LIB.trim().isEmpty()) {
		// File utilLibrary = new File(UTIL_LIB);
		// if (utilLibrary.exists() && utilLibrary.isDirectory()) {
		// for (String jarName : utilLibrary.list()) {
		// if (jarName != null && !jarName.isEmpty() && jarName.endsWith(".jar")) {
		// try {
		// addFileToClassPath(UTIL_LIB + FILE_SEPERATOR + jarName);
		// } catch (IOException e) {
		// LOG.log(Level.SEVERE, "IOException occured ", e);
		// }
		// }
		// }
		// }
		// }
	}

	public APIDifferenceReportDTO findDiffernce(String packageTobeScanned, File prevVersionjarfiles[],
			File currVersionjarfiles[]) {
		List<APIDifferenceReportDetailsDTO> addedMethodReportDTOs = new ArrayList<>();
		List<APIDifferenceReportDetailsDTO> removedMethodReportDTOs = new ArrayList<>();
		List<APIDifferenceReportDetailsDTO> modifiedSignatureChange = new ArrayList<>();
		List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldsAdded = new ArrayList<>();
		List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldRemoved = new ArrayList<>();
		List<APIDifferenceReportDetailsDTO> ambigiousMethodReportDTOs = new ArrayList<>();
		previousVersionClassLoader = buildClassMethodInfoMap(prevVersionjarfiles, previousVersionClassMethodInfoMap);
		currentVersionClassLoader = buildClassMethodInfoMap(currVersionjarfiles, currentVersionClassMethodInfoMap);
		Set<String> completeClassList = new TreeSet<String>();
		completeClassList.addAll(previousVersionClassMethodInfoMap.keySet());
		completeClassList.addAll(currentVersionClassMethodInfoMap.keySet());
		for (String className : completeClassList) {
			if (previousVersionClassMethodInfoMap.containsKey(className)
					&& !currentVersionClassMethodInfoMap.containsKey(className)) {
				removedMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, "NA",
						"Class Removed from current Version", "NA", "NA", "NA", getModuleName(className)));
				Map<String, MethodAttributes> methodAttributeMap = previousVersionClassMethodInfoMap.get(className);
				for (String methodName : methodAttributeMap.keySet()) {
					removedMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, methodName,
							"Method Removed from current Version",
							methodAttributeMap.get(methodName).getFullyQualifiedName(), "NA", "NA",
							getModuleName(className)));
				}
			} else if (!previousVersionClassMethodInfoMap.containsKey(className)
					&& currentVersionClassMethodInfoMap.containsKey(className)) {
				addedMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, "NA",
						"New class added to current Version", "NA", "NA", "NA", getModuleName(className)));
				Map<String, MethodAttributes> methodAttributeMap = currentVersionClassMethodInfoMap.get(className);
				for (String methodName : methodAttributeMap.keySet()) {
					addedMethodReportDTOs.add(
							new APIDifferenceReportDetailsDTO(className, methodName, "Method added in current Version",
									"NA", methodAttributeMap.get(methodName).getFullyQualifiedName(), "NA",
									getModuleName(className)));
				}
			} else {
				Map<String, MethodAttributes> prevMethodAttributeMap = previousVersionClassMethodInfoMap.get(className);
				Map<String, MethodAttributes> currMethodAttributeMap = currentVersionClassMethodInfoMap.get(className);
				Set<String> completeMethodList = new TreeSet<String>();
				completeMethodList.addAll(prevMethodAttributeMap.keySet());
				completeMethodList.addAll(currMethodAttributeMap.keySet());
				for (String methodName : completeMethodList) {
					if (prevMethodAttributeMap.containsKey(methodName)
							&& !currMethodAttributeMap.containsKey(methodName)) {
						removedMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, methodName,
								"Method Removed from current Version",
								prevMethodAttributeMap.get(methodName).getFullyQualifiedName(), "NA", "NA",
								getModuleName(className)));
					} else if (!prevMethodAttributeMap.containsKey(methodName)
							&& currMethodAttributeMap.containsKey(methodName)) {
						addedMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, methodName,
								"Method added in current Version", "NA",
								currMethodAttributeMap.get(methodName).getFullyQualifiedName(), "NA",
								getModuleName(className)));
					} else {
						MethodAttributes prevMethodAttributes = prevMethodAttributeMap.get(methodName);
						MethodAttributes currMethodAttributes = currMethodAttributeMap.get(methodName);
						if (!prevMethodAttributes.equals(currMethodAttributes)) {
							modifiedSignatureChange.add(new APIDifferenceReportDetailsDTO(className, methodName,
									"Method Signature Changed", prevMethodAttributes.getFullyQualifiedName(),
									currMethodAttributes.getFullyQualifiedName(), "NA", getModuleName(className)));
						} else {
							findDTODifference(packageTobeScanned, className, methodName, ambigiousMethodReportDTOs,
									modifiedInputDTOFieldsAdded, modifiedInputDTOFieldRemoved, prevMethodAttributes,
									currMethodAttributes);
						}
					}
				}
			}
		}
		APIDifferenceReportDTO differenceReport = new APIDifferenceReportDTO();
		differenceReport.setAddedMethodReportDTOs(addedMethodReportDTOs);
		differenceReport.setRemovedMethodReportDTOs(removedMethodReportDTOs);
		differenceReport.setModifiedSignatureChange(modifiedSignatureChange);
		differenceReport.setModifiedInputDTOFieldsAdded(modifiedInputDTOFieldsAdded);
		differenceReport.setModifiedInputDTOFieldRemoved(modifiedInputDTOFieldRemoved);
		differenceReport.setAmbigiousMethodReportDTOs(ambigiousMethodReportDTOs);
		return differenceReport;
	}

	private void findDTODifference(String packageTobeScanned, String className, String methodName,
			List<APIDifferenceReportDetailsDTO> ambigiousMethodReportDTOs,
			List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldsAdded,
			List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldRemoved, MethodAttributes prevMethodAttributes,
			MethodAttributes currMethodAttributes) {

		String changedDTOs = null;
		// boolean dtoChanged = false;
		// ClassLoader originalClassLoader =
		// Thread.currentThread().getContextClassLoader();
		for (String inputClass : prevMethodAttributes.getParameters()) {
			if (packageTobeScanned == null || packageTobeScanned.isEmpty()
					|| (inputClass.startsWith(packageTobeScanned)) && !DTO_EXCLUSION_LIST.contains(inputClass)) {
				try {
					// Thread.currentThread().setContextClassLoader(previousVersionClassLoader);
					Class previousVersionClass = previousVersionClassLoader.loadClass(inputClass);
					if (!previousVersionClass.isEnum()) {
						if (previousVersionClass.isInterface()
								|| Modifier.isAbstract(previousVersionClass.getModifiers())) {
							if (previousVersionClass.isInterface()) {
								ambigiousMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, methodName,
										"ArgumentDTO Is an Interface", "NA", "NA", inputClass,
										getModuleName(className)));
							} else {
								Class currentVersionClass = currentVersionClassLoader.loadClass(inputClass);
								findInnerDTODifference(className, methodName, changedDTOs, inputClass,
										ambigiousMethodReportDTOs, modifiedInputDTOFieldsAdded,
										modifiedInputDTOFieldRemoved, null, previousVersionClass, currentVersionClass,
										false, false, true);
							}
						} else {

							Class currentVersionClass = currentVersionClassLoader.loadClass(inputClass);
							findInnerDTODifference(className, methodName, changedDTOs, inputClass,
									ambigiousMethodReportDTOs, modifiedInputDTOFieldsAdded,
									modifiedInputDTOFieldRemoved, null, previousVersionClass, currentVersionClass,
									false, false, false);
						}
					}
				} catch (ClassNotFoundException | NullPointerException e) {
					LOG.log(Level.SEVERE, "Failed in " + inputClass, e);
				}
			}
		}

	}

	private boolean findInnerDTODifference(String className, String methodName, String changedDTOs,
			String currentDTOName, List<APIDifferenceReportDetailsDTO> ambigiousMethodReportDTOs,
			List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldsAdded,
			List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldRemoved, List<String> parentClassList,
			Class previousVersionClass, Class currentVersionClass, boolean isInnerClass, boolean changeAlreadyDetected,
			boolean isArgumentAbstract) {

		if (!changeAlreadyDetected && !previousVersionClass.isEnum()) {
			if (parentClassList == null) {
				parentClassList = new ArrayList<>();
			}
			List<Field> previousVersionfields = Arrays.asList(previousVersionClass.getDeclaredFields());
			List<Field> currentVersionfields = Arrays.asList(currentVersionClass.getDeclaredFields());
			if (!previousVersionfields.toString().equals(currentVersionfields.toString())) {
				if (changedDTOs == null) {
					changedDTOs = currentDTOName;
				} else {
					changedDTOs = changedDTOs + "-->" + currentDTOName;
				}
				if (!changedDTOs.equals(previousVersionClass.getName())) {
					changedDTOs = changedDTOs + "-->" + previousVersionClass.getName();
				}

				if (previousVersionfields.toString().length() > currentVersionfields.toString().length()) {
					modifiedInputDTOFieldRemoved.add(new APIDifferenceReportDetailsDTO(className, methodName,
							"ArgumentDTO changed - Fields Removed", "NA", "NA", changedDTOs, getModuleName(className)));
				} else {
					modifiedInputDTOFieldsAdded.add(new APIDifferenceReportDetailsDTO(className, methodName,
							"ArgumentDTO changed - Fields Added", "NA", "NA", changedDTOs, getModuleName(className)));
				}
				changeAlreadyDetected = true;
			} else {
				/**
				 * If fields are matching then this means inner DTO only chance is inner DTO is
				 * changed, hence fields of previous and current versions will be same hence we
				 * can loop through any one of that.
				 */
				for (Field field : previousVersionfields) {
					String fieldClassName = field.getType().getName();
					if (fieldClassName.startsWith("com.ofss") && !DTO_EXCLUSION_LIST.contains(fieldClassName)) {
						try {
							Class previousVersionInnerClass = previousVersionClassLoader.loadClass(fieldClassName);
							Class currentVersionInnerClass = currentVersionClassLoader.loadClass(fieldClassName);
							if (!parentClassList.contains(previousVersionClass.getName())) {
								parentClassList.add(previousVersionClass.getName());
							}
							if (!parentClassList.contains((previousVersionInnerClass.getName()))) {
								changeAlreadyDetected = findInnerDTODifference(className, methodName, changedDTOs,
										currentDTOName, ambigiousMethodReportDTOs, modifiedInputDTOFieldsAdded,
										modifiedInputDTOFieldRemoved, parentClassList, previousVersionInnerClass,
										currentVersionInnerClass, true, changeAlreadyDetected, isArgumentAbstract);
							}
						} catch (ClassNotFoundException e) {
							LOG.log(Level.SEVERE,
									"Failed in Inner class of " + className + "--> Inner ClassName " + fieldClassName,
									e);
						}
					}
				}
				if (isArgumentAbstract && !changeAlreadyDetected && !isInnerClass) {
					ambigiousMethodReportDTOs.add(new APIDifferenceReportDetailsDTO(className, methodName,
							"Argument is Abstract maybe Concrete class might have changed", "NA", "NA", currentDTOName,
							getModuleName(className)));
				}
			}
		}
		return changeAlreadyDetected;
	}

	private String getModuleName(String className) {

		String module = null;
		if (className.startsWith("com.ofss.fc.appx.")) {
			module = className.replace("com.ofss.fc.appx.", "");
			module = module.substring(0, module.indexOf("."));
		} else if (className.startsWith("com.ofss.fc.app.")) {
			module = className.replace("com.ofss.fc.app.", "");
			module = module.substring(0, module.indexOf("."));
		}
		return module;
	}

	private URLClassLoader buildClassMethodInfoMap(File jarfiles[],
			Map<String, Map<String, MethodAttributes>> classMethodInfoMap) {

		URLClassLoader cl = null;
		try {

			if (jarfiles != null && jarfiles.length > 0) {

				cl = buildNewClassLoader(jarfiles);
				for (File file : jarfiles) {
					if (file != null && file.getName().endsWith(".jar")) {
						JarFile jarFile = new JarFile(file);
						for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
							JarEntry entry = e.nextElement();
							if (entry.getName().endsWith(".class") && !CLASS_EXCLUSION_LIST.contains(entry.getName())) {
								String qualifiedClassName = entry.getName().replace(".class", "");
								qualifiedClassName = qualifiedClassName.replaceAll(FILE_SEPERATOR, ".");
								String className = qualifiedClassName.substring(qualifiedClassName.lastIndexOf(".") + 1,
										qualifiedClassName.length());
								boolean endsCorrectly = (API_NAMING_PATTERN_END == null
										|| API_NAMING_PATTERN_END.trim().isEmpty()) ? true
												: className.endsWith(API_NAMING_PATTERN_END);
								boolean startsCorrectly = (API_NAMING_PATTERN_START == null
										|| API_NAMING_PATTERN_START.trim().isEmpty()) ? true
												: className.startsWith(API_NAMING_PATTERN_START);
								if (startsCorrectly && endsCorrectly) {
									Map<String, MethodAttributes> methodsAttributesmap = new HashMap<String, MethodAttributes>();
									try {
										Class reflectedClass = cl.loadClass(qualifiedClassName);
										Method[] allMethods = reflectedClass.getDeclaredMethods();
										for (Method method : allMethods) {
											SortedSet<String> parameters = new TreeSet<String>();
											for (Class param : method.getParameterTypes()) {
												parameters.add(param.getName());
											}
											MethodAttributes methodAttributes = new MethodAttributes(
													method.toString().replace(",", ";"), method.getName(),
													method.getReturnType().getName(), method.getParameterCount(),
													parameters);
											methodsAttributesmap.put(method.getName(), methodAttributes);
										}
										classMethodInfoMap.put(qualifiedClassName, methodsAttributesmap);
									} catch (ClassNotFoundException ex) {
										LOG.log(Level.SEVERE, "ClassNotFoundException jar :->" + file.getName(), ex);
									}
								}
							}
						}
						jarFile.close();
					} else {
						LOG.log(Level.SEVERE, "Not a Jar File ::" + file.getName());
					}
				}
			} else {
				LOG.log(Level.SEVERE, "Not Jars to be processed");
			}
		} catch (IOException | SecurityException e) {
			LOG.log(Level.SEVERE, "IOException Occured", e);
		}
		return cl;
	}

	/**
	 * Adds a file to a new classLoader which is different from default
	 * SystemClassLoader.
	 * 
	 * @param filePaths
	 *            a String pointing to the file
	 * @throws IOException
	 */
	private static URLClassLoader buildNewClassLoader(File jarfiles[]) throws IOException {

		if (jarfiles != null && jarfiles.length > 0) {
			List<URL> files = new ArrayList<URL>();
			for (File f : jarfiles) {

				files.add(f.toURI().toURL());
			}
			URLClassLoader clazzLoader = new URLClassLoader(files.toArray(new URL[files.size()]));
			return clazzLoader;
		} else {
			return (URLClassLoader) ClassLoader.getSystemClassLoader();
		}
	}

	/**
	 * Parameters of the method to add an URL to the System classes.
	 */
	private static final Class<?>[] parameters = new Class[] { URL.class };

	/**
	 * Adds a file to the default SystemClassLoader.
	 * 
	 * @param filePath
	 *            a String pointing to the file
	 * @throws IOException
	 */
	private static void addFileToClassPath(String filePath) throws IOException {

		File f = new File(filePath);
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class<?> sysclass = URLClassLoader.class;
		try {
			Method method = sysclass.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			// Adds the content pointed by the URL to the SystemClassLoader.
			Object obj = method.invoke(sysloader, new Object[] { f.toURI().toURL() });
		} catch (Throwable t) {
			LOG.log(Level.SEVERE, "Exception Occured in addFileToClassPath()", t);
			throw new IOException("Error, could not add URL to system classloader");
		}
	}
}
