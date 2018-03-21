
package org.shail.api.diff.file.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.shail.api.diff.core.ApiDifferenceFinder;
import org.shail.api.diff.file.upload.form.FileUploadForm;
import org.shail.api.diff.report.report.dto.APIDifferenceReportDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@GetMapping("/fileUploadForm")
	public String fileUploadForm(Model model) {

		return "fileUploadForm";
	}
	// Handling file upload request
	/*
	 * @PostMapping("/fileUpload") public ResponseEntity<Object>
	 * fileUpload(@RequestParam("oldfile") MultipartFile oldfile,
	 * 
	 * @RequestParam("newfile") MultipartFile newfile) throws IOException {
	 * 
	 * // Save file on system if (!oldfile.getOriginalFilename().isEmpty() &&
	 * !newfile.getOriginalFilename().isEmpty()) {
	 * 
	 * ApiDifferenceFinder finder = new ApiDifferenceFinder();
	 * 
	 * File oldJarfile = new File(oldfile.getOriginalFilename());
	 * oldJarfile.createNewFile(); FileOutputStream fos = new
	 * FileOutputStream(oldJarfile); fos.write(oldfile.getBytes()); fos.close();
	 * 
	 * File newJarfile = new File(newfile.getOriginalFilename());
	 * newJarfile.createNewFile(); FileOutputStream fos1 = new
	 * FileOutputStream(newJarfile); fos1.write(newfile.getBytes()); fos1.close();
	 * 
	 * APIDifferenceReportDTO differenceReport = finder.findDiffernce(new File[] {
	 * oldJarfile }, new File[] { newJarfile });
	 * System.out.println(differenceReport); } else { return new
	 * ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST); }
	 * 
	 * return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK); }
	 */

	// Handling file upload request
	@PostMapping("/fileUpload")
	public ResponseEntity<Object> fileUpload(@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model map) {

		try {
			List<MultipartFile> oldfiles = uploadForm.getOldVersionJars();
			List<MultipartFile> newfiles = uploadForm.getNewVersionJars();
			APIDifferenceReportDTO differenceReport = null;
			// Save file on system
			if (oldfiles != null && newfiles != null && !oldfiles.isEmpty() && !newfiles.isEmpty()) {
				try {
					List<File> newVersionJarFiles = new ArrayList<>();
					List<File> oldVersionJarFiles = new ArrayList<>();
					for (MultipartFile multipartFile : newfiles) {
						if ( !multipartFile.getOriginalFilename().isEmpty()) {
							if (multipartFile.getOriginalFilename().endsWith(".jar")) {
								File jarfile = new File(multipartFile.getOriginalFilename());
								jarfile.createNewFile();
								FileOutputStream fos = new FileOutputStream(jarfile);
								fos.write(multipartFile.getBytes());
								fos.close();
								newVersionJarFiles.add(jarfile);
							} else {
								return new ResponseEntity<Object>("Invalid file, Only jars expected.", HttpStatus.EXPECTATION_FAILED);
							}
						}
					}
					for (MultipartFile multipartFile : oldfiles) {
						if ( !multipartFile.getOriginalFilename().isEmpty()) {
							if (multipartFile.getOriginalFilename().endsWith(".jar")) {
								File jarfile = new File(multipartFile.getOriginalFilename());
								jarfile.createNewFile();
								FileOutputStream fos = new FileOutputStream(jarfile);
								fos.write(multipartFile.getBytes());
								fos.close();
								oldVersionJarFiles.add(jarfile);
							} else {
								return new ResponseEntity<Object>("Invalid file, Only jars expected.", HttpStatus.EXPECTATION_FAILED);
							}
						}
					}
					if (oldVersionJarFiles.isEmpty() || newVersionJarFiles.isEmpty()) {
						return new ResponseEntity<Object>("Both Version Jars required.", HttpStatus.EXPECTATION_FAILED);
					}
					ApiDifferenceFinder finder = new ApiDifferenceFinder();
					differenceReport = finder.findDiffernce(oldVersionJarFiles.toArray(new File[oldVersionJarFiles.size()]),
					                                        newVersionJarFiles.toArray(new File[newVersionJarFiles.size()]));
				} catch (IOException e) {
					return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<Object>("Invalid file.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>("File Uploaded Successfully." + differenceReport.toString(), HttpStatus.OK);
		} catch (Throwable e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}