package com.usfEmpMgmt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.usfEmpMgmt.DashJDBCTemp;

@Controller

public class EmpController {
	int empId = 0;
	String userName = null;
	@Autowired
	DashJDBCTemp dashJDBCTemp;

	@RequestMapping("/home")
	public String Login() {

		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView submitLogin(@RequestParam String username, @RequestParam String password) {
		// -----------------Login Success
		if (dashJDBCTemp.verifyLogin(username, password) == true) {
			//   HttpSession session=request.getSession();          
			// session.setAttribute("uname",n);  

			empId = dashJDBCTemp.getEmpId(username);
			userName = dashJDBCTemp.getEmpName(empId).toLowerCase();
			// -----------------Manager
			if (dashJDBCTemp.getEmpCategory(username) == 7003) {
				ModelAndView model = new ModelAndView("redirect:/ManagerDash");
				return model;
			}
			// -----------------Employee
			else {
				ModelAndView model = new ModelAndView("redirect:/Dashboard");
				return model;
			}
		}
		// -----------------Login Fail
		else {
			ModelAndView model = new ModelAndView("Login");
			model.addObject("Fail", "Invalid Credentials");
			return model;
		}
	}

	@RequestMapping(value = "/Dashboard")
	public ModelAndView Dashboard() {
		List<Activity> act = dashJDBCTemp.getDash(empId);
		ModelAndView model = new ModelAndView("Dashboard");
		model.addObject("userName", userName);
		model.addObject("act", act);

		Integer leaves = dashJDBCTemp.getLeavesRemain(empId);
		model.addObject("leaves", leaves);

		List<Training> training = dashJDBCTemp.getTrainingsDash(empId);
		model.addObject("training", training);

		List<Incentives> incentive = dashJDBCTemp.getIncentivesDash(empId);
		model.addObject("incentive", incentive);

		return model;
	}

	@RequestMapping(value = "/Shift")
	public String Shift() {
		return "Shift";
	}

	@RequestMapping(value = "/ManagerDash")
	public ModelAndView ManagerDash() {
		ModelAndView model = new ModelAndView("DashMgr");
		model.addObject("userName", userName);
		Integer leavesPending = dashJDBCTemp.getMgrLeavesPending();
		model.addObject("leavesPending", leavesPending);
		Integer accessoriesPending = dashJDBCTemp.getAccessoriesPending();
		model.addObject("accessoriesPending", accessoriesPending);
		return model;
	}

	@RequestMapping(value = "/shiftReq")
	@ResponseBody()
	public String ShiftReq(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<Shift> shift = dashJDBCTemp.getShift(empId, startDate, endDate);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(shift);
		return jsonInString;
	}

	@RequestMapping(value = "/activityReq")
	@ResponseBody()
	public String ActivityReq(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<Activity> activity = dashJDBCTemp.getActivity(empId, startDate, endDate);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(activity);
		return jsonInString;
	}

	@RequestMapping(value = "/Activity")
	public String Activity() {
		return "/Activity";
	}

	@RequestMapping(value = "/ActivityMgr")
	public String ActivityMgr() {
		return "/ActMgr";
	}

	@RequestMapping(value = "/actReqMgr")
	@ResponseBody()
	public String ActivityMgr(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<ActivityManager> actMgr = dashJDBCTemp.getActMgr(startDate, endDate);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(actMgr);
		return jsonInString;
	}

	@RequestMapping(value = "/Leaves")
	public ModelAndView Leaves() {
		ModelAndView model = new ModelAndView("Leaves");
		Integer leavesRemain = dashJDBCTemp.getLeavesRemain(empId);
		model.addObject("leavesRemain", leavesRemain);
		return model;
	}

	@RequestMapping(value = "/requestLeave")
	@ResponseBody
	public String requestLeaves(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("reason") String reason) {
		dashJDBCTemp.applyLeave(empId, startDate, endDate, reason);
		// Integer leavesRemain=dashJDBCTemp.getLeavesRemain(empId);
		return "Applied";
	}

	@RequestMapping(value = "/leaveHist")
	@ResponseBody
	public String leaveHistory(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<Leaves> leaves = dashJDBCTemp.getLeaveHist(empId, startDate, endDate);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(leaves);
		return jsonInString;
	}

	@RequestMapping(value = "/Availability")
	public ModelAndView Availability() {
		ModelAndView model = new ModelAndView("Availability");
		List<Availability> availability = dashJDBCTemp.getAvailability(empId);
		model.addObject("availability", availability);
		return model;
	}

	@RequestMapping(value = "/updateAvailability")
	public String UpdateAvailability() {
		return "UpdateAvailability";
	}

	@RequestMapping(value = "/updAvailSubmit")
	public String UpdAvailSubmit(@RequestParam String dayofWeek, @RequestParam String startTime,
			@RequestParam String endTime) {
		System.out.println("time:"+dayofWeek+startTime+endTime);
		dashJDBCTemp.updateAvail(dayofWeek, startTime, endTime, empId);
		return "UpdateAvailability";
	}

	@RequestMapping(value = "/Training")
	public ModelAndView Training() {
		ModelAndView model = new ModelAndView("Training");
		List<Training> training = dashJDBCTemp.getTrainings(empId);
		model.addObject("training", training);
		return model;
	}

	@RequestMapping(value = "/Timesheet")
	public ModelAndView Timesheet() {
		ModelAndView model = new ModelAndView("Timesheet");
		List<Timesheet> timesheet = dashJDBCTemp.getTimesheet(empId);
		model.addObject("timesheet", timesheet);
		return model;
	}

	@RequestMapping(value = "/Payments")
	public ModelAndView Payments() {
		ModelAndView model = new ModelAndView("Payments");
		List<Payments> payment = dashJDBCTemp.getPayments(empId);
		model.addObject("payment", payment);
		return model;
	}

	@RequestMapping(value = "/Accessories")
	public ModelAndView Accessories() {
		ModelAndView model = new ModelAndView("Accessories");
		List<AccessoryIssue> access = dashJDBCTemp.getAccessoriesIssued(empId);
		model.addObject("access", access);
		return model;
	}

	@RequestMapping(value = "/Incentives")
	public ModelAndView Incentives() {
		ModelAndView model = new ModelAndView("Incentives");
		List<Incentives> incentive = dashJDBCTemp.getIncentives(empId);
		model.addObject("incentive", incentive);
		return model;
	}

	@RequestMapping(value = "/Performance")
	public ModelAndView Performance() {
		ModelAndView model = new ModelAndView("Performance");
		List<Performance> perform = dashJDBCTemp.getPerformance(empId);
		model.addObject("perform", perform);
		return model;
	}

	@RequestMapping(value = "/LeaveMgr")
	public String LeaveMgr() {
		return "/LeaveMgr";
	}

	@RequestMapping(value = "/reqLeaveMgr")
	@ResponseBody
	public String ReqLeaveMgr(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<LeaveMgr> leaves = dashJDBCTemp.getLeaveMgr(startDate, endDate);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(leaves);
		return jsonInString;
	}

	@RequestMapping(value = "/PerformMgr")
	public ModelAndView PerformMgr() {
		ModelAndView model = new ModelAndView("PerformMgr");
		List<PerformMgr> perform = dashJDBCTemp.getPerformMgr();
		model.addObject("perform", perform);
		return model;
	}

	@RequestMapping(value = "/AvailabilityMgr")
	public String AvailabilityMgr() {
		return "/AvailabilityMgr";
	}

	@RequestMapping(value = "/reqAvailabilityMgr")
	@ResponseBody
	public String ReqAvailabilityMgr(@RequestParam String dayofWeek) {
		List<AvailabilityMgr> avail = dashJDBCTemp.getAvailabilityMgr(dayofWeek);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(avail);
		return jsonInString;
	}

	// @RequestMapping(value = "/ApproveLeavesForm")
	// public String ApproveLeavesForm() {
	// System.out.println("aprove");
	// return "/LeavesApproval";
	// }

	@RequestMapping(value = "/ApproveLeavesForm")
	public ModelAndView ApproveLeavesForm() {
		ModelAndView model = new ModelAndView("LeavesApproval");
		List<LeaveMgr> leaves = dashJDBCTemp.getApproveLeaves();
		model.addObject("leaves", leaves);
		System.out.println("aprove1");
		return model;

	}

	@RequestMapping(value = "/reqLeaveApprove")
	@ResponseBody
	public String ApproveLeaves(@RequestParam(value = "leaveApproveId[]") List<Integer> leaveIds,
			@RequestParam String action) {
		if (action.equals("approve")) {
			dashJDBCTemp.ApproveLeaves(leaveIds);
			return "succesfully approved";
		} else {
			dashJDBCTemp.RejectLeaves(leaveIds);
			return "succesfully rejected";
		}

	}

	@RequestMapping(value = "/ViewProfile")
	public ModelAndView ViewProfile() {
		ModelAndView model = new ModelAndView("Profile");
		Profile profile = dashJDBCTemp.getProfile(empId);
		model.addObject("profile", profile);
		return model;

	}

	@RequestMapping(value = "/AccessoriesIssueForm")
	public String AccessoriesIssueForm() {
		return "AccessoriesMgr";
	}

	@RequestMapping(value = "/ManageAccessory")
	public ModelAndView ManageAccessory(AccessoriesUpdate accessoriesUpdate) {
		dashJDBCTemp.updateAccessories(accessoriesUpdate);
		ModelAndView model = new ModelAndView("AccessoriesMgr");
		model.addObject("Access", "Accessories Updated Scuccessfully");
		return model;
	}

	@RequestMapping(value = "/PerformaneForm")
	public String PerformaneForm() {
		return "Rating";
	}

	@RequestMapping(value = "/Rating")
	public ModelAndView Rating(@RequestParam String employeeId, @RequestParam String ratingPeriod,
			@RequestParam String rating) {
		dashJDBCTemp.rate(employeeId, ratingPeriod, rating, empId);
		ModelAndView model = new ModelAndView("Rating");
		model.addObject("Access", "Rating Updated Scuccessfully");
		return model;
	}

}
