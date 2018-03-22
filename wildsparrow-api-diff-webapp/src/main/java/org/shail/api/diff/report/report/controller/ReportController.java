package org.shail.api.diff.report.report.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shail.api.diff.report.report.dto.APIDifferenceReportDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class ReportController {

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView displayReport(HttpServletRequest request, HttpServletResponse response, Model model,
			ModelMap modelmap, ModelAndView modelAndView, RedirectAttributes redirectAttributes,
			RedirectAttributesModelMap redirectAttributesModelMap) {

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		APIDifferenceReportDTO differenceReport = (APIDifferenceReportDTO) flashMap.get("differenceReport");

		return new ModelAndView("report", "report", differenceReport);
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView gotoHome(HttpServletRequest request, HttpServletResponse response, Model model,
			ModelMap modelmap, ModelAndView modelAndView, RedirectAttributes redirectAttributes,
			RedirectAttributesModelMap redirectAttributesModelMap) {

		return new ModelAndView("home");
	}

}