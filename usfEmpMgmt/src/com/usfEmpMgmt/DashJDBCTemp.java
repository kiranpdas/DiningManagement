package com.usfEmpMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DashJDBCTemp {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean verifyLogin(String userId, String pwd) {
		String SQL = "select count(1) from USERCREDENTIALS where username=? and password=?";
		Integer count = jdbcTemplate.queryForObject(SQL, new Object[] { userId, pwd }, Integer.class);
		if (count > 0)
			return true;
		else
			return false;

	}

	public List<Activity> getDash(Integer empId) {

		String SQL = "SELECT extract (MONTH FROM STARTTIME)\r\n" + 
				"  ||'-'\r\n" + 
				"  ||extract (DAY FROM STARTTIME)\r\n" + 
				"  ||'-'\r\n" + 
				"  ||EXTRACT (YEAR FROM STARTTIME) SHD_DATE,\r\n" + 
				"  lpad(extract (hour FROM STARTTIME),2,'0')\r\n" + 
				"  ||':'\r\n" + 
				"  ||LPAD(EXTRACT (MINUTE FROM STARTTIME),2,'0') STARTTIME,\r\n" + 
				"  lpad(extract (hour FROM ENDTIME),2,'0')\r\n" + 
				"  ||':'\r\n" + 
				"  ||lpad(extract (minute FROM ENDTIME),2,'0') ENDTIME,\r\n" + 
				"  ac.SPECIFICACTIVITY activity,\r\n" + 
				"  dl.LOCATION,\r\n" + 
				"  spr.EMPFIRSTNAME\r\n" + 
				"  ||' '\r\n" + 
				"  ||EMPLASTNAME supervisor,STARTTIME\r\n" + 
				"FROM activitytable ac\r\n" + 
				"JOIN scheduletable sh\r\n" + 
				"ON sh.SCHEDULEID = ac.SCHEDULEID\r\n" + 
				"JOIN dininglocations dl\r\n" + 
				"ON dl.locationid=ac.LOCATIONID\r\n" + 
				"JOIN employeedetails spr\r\n" + 
				"ON spr.empid   =ac.supervisorid\r\n" + 
				"WHERE sh.empid =?\r\n" + 
				"AND STARTTIME  >SYSDATE\r\n" + 
				"AND STARTTIME  <SYSDATE+3";

		return jdbcTemplate.query(SQL, new Object[] { empId }, new ActMapper());

	}

	public List<Shift> getShift1(Integer id) {
		String SQL = "SELECT STARTTIME,ENDTIME FROM SCHEDULETABLE where EMPID=?";
		return jdbcTemplate.query(SQL, new Object[] { id }, new ShiftMapper());
	}

	public List<Shift> getShift(Integer empId, String startDate, String endDate) {
		String SQL = "SELECT extract (MONTH FROM STARTTIME)\r\n" + "  ||'-'\r\n"
				+ "  ||extract (DAY FROM STARTTIME)\r\n" + "  ||'-'\r\n" + "  ||extract (YEAR FROM STARTTIME) dt ,\r\n"
				+ "  extract (hour FROM STARTTIME)\r\n" + "  ||':'\r\n"
				+ "  ||extract (minute FROM STARTTIME) STARTTIME ,\r\n" + "  extract (hour FROM ENDTIME)\r\n"
				+ "  ||':'\r\n" + "  ||extract (minute FROM ENDTIME) ENDTIME\r\n" + "FROM SCHEDULETABLE\r\n"
				+ "WHERE EMPID=?\r\n" + "AND starttime BETWEEN TO_DATE(?, 'yyyy/mm/dd') AND TO_DATE(?, 'yyyy/mm/dd')";
		return jdbcTemplate.query(SQL, new Object[] { empId, startDate, endDate }, new ShiftMapper());
	}

	public List<AccessoryIssue> getAccessoriesIssued(Integer empId) {

		String SQL = "SELECT ea.ACCESSORYNAME,\r\n" + "  ai.ACCESSORYISSUESTATUS,\r\n" + "  ai.ACCESSORYISSUEDATE\r\n"
				+ "FROM ACCESSORYISSUE ai\r\n" + "JOIN EMPLOYEEACCESSORIES ea\r\n"
				+ "ON ea.ACCESSORYID = ai.ACCESSORYID \r\n" + "where ai.EMPID=?";
		return jdbcTemplate.query(SQL, new Object[] { empId }, new AccessMapper());

	}

	public List<Performance> getPerformance(Integer empId) {
		String SQL = "SELECT ep.ratingperiod,\r\n" + "  EMP.EMPFIRSTNAME\r\n" + "  ||' '\r\n"
				+ "  ||emp.EMPLASTNAME APPRAISER,\r\n" + "  EP.EMPRATING\r\n" + "FROM EMPLOYEEPERFORMANCE ep\r\n"
				+ "JOIN EMPLOYEEDETAILS emp\r\n" + "ON ep.ratingproviderid=emp.empid\r\n"
				+ "AND ep.empid          =?\r\n" + "ORDER BY ratingperiod DESC";

		return jdbcTemplate.query(SQL, new Object[] { empId }, new PerformMapper());
	}

	public List<Incentives> getIncentives(int empId) {
		String SQL = "SELECT COUPONID,\r\n" + "  COUPONDESC,\r\n" + "  COUPONREDEEMEDSTATUS\r\n" + "FROM incentives\r\n"
				+ "WHERE empid=?";
		return jdbcTemplate.query(SQL, new Object[] { empId }, new IncentivesMapper());
	}

	public List<Activity> getActivity(int empId, String startDate, String endDate) {
		String SQL = "SELECT extract (MONTH FROM STARTTIME)\r\n" + "  ||'-'\r\n"
				+ "  ||extract (DAY FROM STARTTIME)\r\n" + "  ||'-'\r\n"
				+ "  ||extract (YEAR FROM STARTTIME) shd_date,\r\n" + "  extract (hour FROM STARTTIME)\r\n"
				+ "  ||':'\r\n" + "  ||extract (minute FROM STARTTIME) STARTTIME ,\r\n"
				+ "  extract (hour FROM ENDTIME)\r\n" + "  ||':'\r\n" + "  ||extract (minute FROM ENDTIME) ENDTIME,\r\n"
				+ "  ac.SPECIFICACTIVITY activity,\r\n" + "  dl.LOCATION,\r\n" + "  spr.EMPFIRSTNAME\r\n"
				+ "  ||' '\r\n" + "  ||EMPLASTNAME supervisor\r\n" + "FROM activitytable ac\r\n"
				+ "JOIN scheduletable sh\r\n" + "ON sh.SCHEDULEID = ac.SCHEDULEID\r\n" + "JOIN dininglocations dl\r\n"
				+ "ON dl.locationid=ac.LOCATIONID\r\n" + "JOIN employeedetails spr\r\n"
				+ "ON spr.empid   =ac.supervisorid\r\n" + "WHERE sh.empid =?\r\n"
				+ "AND starttime BETWEEN TO_DATE(?, 'yyyy/mm/dd') AND TO_DATE(?, 'yyyy/mm/dd')";
		return jdbcTemplate.query(SQL, new Object[] { empId, startDate, endDate }, new ActMapper());
	}

	public Integer getLeavesRemain(int empId) {
		String SQL = "SELECT cat.leavesentitled-inv.leavesavailed leaves_remaining\r\n" + 
				"FROM LEAVESINVENTORY inv\r\n" + 
				"JOIN employeedetails emp\r\n" + 
				"ON emp.empid=inv.empid\r\n" + 
				"JOIN CATEGORYTABLE cat\r\n" + 
				"ON emp.EMPCATID =cat.EMPCATID\r\n" + 
				"WHERE inv.empid =?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { empId }, Integer.class);

	}

	public void applyLeave(int empId, String startDate, String endDate, String reason) {
		
		String sql = "insert into leavestable values(LEAVE_SEQ.NEXTVAL,?,?,'pending',TO_DATE(?, 'yyyy/mm/dd'),TO_DATE(?, 'yyyy/mm/dd'))";
		jdbcTemplate.update(sql, new Object[] {  empId, reason, startDate, endDate });

	}

	public List<Leaves> getLeaveHist(int empId, String startDate, String endDate) {
		String sql = "SELECT LEAVESTART,\r\n" + "  LEAVEEND,\r\n" + "  LEAVEREASON,\r\n" + "  APPROVALSTATUS\r\n"
				+ "FROM leavestable\r\n" + "WHERE empid =?\r\n"
				+ "AND LEAVESTART BETWEEN TO_DATE(?, 'yyyy/mm/dd') AND TO_DATE(?, 'yyyy/mm/dd')";
		return jdbcTemplate.query(sql, new Object[] { empId, startDate, endDate }, new LeaveMapper());

	}

	public List<Timesheet> getTimesheet(int empId) {
		String sql = "select checkin,checkout from TIMESHEETTABLE where empid=?";
		return jdbcTemplate.query(sql, new Object[] { empId }, new TimesheetMapper());
	}

	public List<Payments> getPayments(int empId) {
		String sql = "select paymentdate,amountpaid from PAYMENTSTABLE where empid=?";
		return jdbcTemplate.query(sql, new Object[] { empId }, new PaymentMapper());
	}

	public List<Training> getTrainings(int empId) {
		String sql = " select ta.TRAININGDESC,tt.TRAININGASSIGNEDDATE, tt.STATUS from TRAININGSAVAILABLE ta join TRAININGSTRACKER tt on  tt.trainingid=ta.trainingid\r\n"
				+ "        where tt.empid=?";
		return jdbcTemplate.query(sql, new Object[] { empId }, new TrainingMapper());
	}

	public List<Availability> getAvailability(int empId) {
		String sql = "SELECT AVAIL_DAY, START_TIME, END_TIME FROM availability WHERE empid=?";
		return jdbcTemplate.query(sql, new Object[] { empId }, new AvailabilityMapper());
	}

	public void updateAvail(String dayofWeek, String startTime, String endTime, int empId) {
		String sql = " merge INTO availability av USING\r\n" + 
				"(SELECT ? empid, ? start_time, ? end_time, ? avail_day FROM dual\r\n" + 
				")dl ON (dl.empid= av.empid AND dl.avail_day=av.avail_day)\r\n" + 
				"WHEN matched THEN\r\n" + 
				"  UPDATE\r\n" + 
				"  SET av.start_time=dl.start_time,\r\n" + 
				"    av.end_time    =dl.end_time WHEN NOT matched THEN\r\n" + 
				"  INSERT VALUES\r\n" + 
				"    (dl.avail_day, dl.start_time, dl.end_time, dl.empid\r\n" + 
				"    )";
		jdbcTemplate.update(sql, new Object[] { empId,startTime, endTime, dayofWeek  });

	}

	public int getEmpId(String username) {
		String SQL = "select empid from USERCREDENTIALS where username=?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { username }, Integer.class);
	}

	public String getEmpName(int empId) {
		String SQL = "select EMPFIRSTNAME||' '||EMPLASTNAME emp_name from employeedetails where empid=?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { empId }, String.class);
	}

	public int getEmpCategory(String username) {
		String SQL = "SELECT empcatid\r\n" + "FROM usercredentials usr\r\n" + "JOIN employeedetails emp\r\n"
				+ "ON usr.empid      =emp.empid\r\n" + "WHERE usr.USERNAME=?";
		return jdbcTemplate.queryForObject(SQL, new Object[] { username }, Integer.class);
	}

	public List<ActivityManager> getActMgr(String startDate, String endDate) {
		String SQL = "SELECT emp.empid,\r\n" + "  emp.empfirstname\r\n" + "  ||' '\r\n"
				+ "  ||emp.emplastname emp_name,\r\n" + "  extract (MONTH FROM STARTTIME)\r\n" + "  ||'-'\r\n"
				+ "  ||extract (DAY FROM STARTTIME)\r\n" + "  ||'-'\r\n"
				+ "  ||extract (YEAR FROM STARTTIME) shd_date,\r\n" + "  extract (hour FROM STARTTIME)\r\n"
				+ "  ||':'\r\n" + "  ||extract (minute FROM STARTTIME) STARTTIME ,\r\n"
				+ "  extract (hour FROM ENDTIME)\r\n" + "  ||':'\r\n" + "  ||extract (minute FROM ENDTIME) ENDTIME,\r\n"
				+ "  ac.SPECIFICACTIVITY activity,\r\n" + "  dl.LOCATION,\r\n" + "  spr.EMPFIRSTNAME\r\n"
				+ "  ||' '\r\n" + "  ||spr.EMPLASTNAME supervisor\r\n" + "FROM activitytable ac\r\n"
				+ "JOIN scheduletable sh\r\n" + "ON sh.SCHEDULEID = ac.SCHEDULEID\r\n" + "JOIN dininglocations dl\r\n"
				+ "ON dl.locationid=ac.LOCATIONID\r\n" + "JOIN employeedetails spr\r\n"
				+ "ON spr.empid =ac.supervisorid\r\n" + "JOIN employeedetails emp\r\n" + "ON emp.empid =sh.empid\r\n"
				+ "WHERE starttime BETWEEN TO_DATE(?, 'yyyy/mm/dd') AND TO_DATE(?, 'yyyy/mm/dd')";
		return jdbcTemplate.query(SQL, new Object[] { startDate, endDate }, new ActMgrMapper());
	}

	public List<LeaveMgr> getLeaveMgr(String startDate, String endDate) {
		String SQL = "SELECT emp.EMPID,lv.LEAVEID,\r\n" + "  emp.empfirstname\r\n" + "  ||' '\r\n"
				+ "  ||emp.emplastname emp_name,\r\n" + "  lv.LEAVESTART,\r\n" + "  lv.LEAVEEND,\r\n"
				+ "  lv.LEAVEREASON,\r\n" + "  lv.APPROVALSTATUS\r\n" + "FROM leavestable lv\r\n"
				+ "JOIN employeedetails emp\r\n" + "ON emp.empid=lv.empid\r\n"
				+ "WHERE lv.LEAVESTART BETWEEN TO_DATE(?, 'yyyy/mm/dd') AND TO_DATE(?, 'yyyy/mm/dd')";
		return jdbcTemplate.query(SQL, new Object[] { startDate, endDate }, new LeaveMgrMapper());
	}

	public List<PerformMgr> getPerformMgr() {
		String sql = "SELECT emp.empid,\r\n" + "  emp.empfirstname\r\n" + "  ||' '\r\n"
				+ "  ||emp.emplastname emp_name,\r\n" + "  perf.ratingperiod,\r\n" + "  spr.EMPFIRSTNAME\r\n"
				+ "  ||' '\r\n" + "  ||spr.emplastname appraiser,\r\n" + "  perf.emprating\r\n"
				+ "FROM EMPLOYEEPERFORMANCE perf\r\n" + "JOIN employeedetails emp\r\n" + "ON emp.empid=perf.empid\r\n"
				+ "JOIN employeedetails spr\r\n" + "ON spr.empid=perf.RATINGPROVIDERID";
		return jdbcTemplate.query(sql, new PerformMgrMapper());
	}

	public List<AvailabilityMgr> getAvailabilityMgr(String dayofWeek) {
		String sql = "SELECT av.AVAIL_DAY,\r\n" + "  av.START_TIME,\r\n" + "  av.END_TIME,\r\n" + "  emp.EMPID,\r\n"
				+ "  emp.empfirstname\r\n" + "  ||' '\r\n" + "  ||emp.emplastname emp_name\r\n"
				+ "FROM availability av\r\n" + "JOIN employeedetails emp\r\n" + "ON emp.EMPID = av.EMPID\r\n"
				+ "where upper(av.AVAIL_DAY)=upper(?)";
		return jdbcTemplate.query(sql, new Object[] { dayofWeek }, new AvailabilityMgrMapper());
	}

	public List<LeaveMgr> getApproveLeaves() {
		String SQL = "SELECT emp.EMPID,lv.LEAVEID,\r\n" + "  emp.empfirstname\r\n" + "  ||' '\r\n"
				+ "  ||emp.emplastname emp_name,\r\n" + "  lv.LEAVESTART,\r\n" + "  lv.LEAVEEND,\r\n"
				+ "  lv.LEAVEREASON,\r\n" + "  lv.APPROVALSTATUS\r\n" + "FROM leavestable lv\r\n"
				+ "LEFT JOIN employeedetails emp\r\n" + "ON emp.empid=lv.empid\r\n" + "WHERE lower(lv.APPROVALSTATUS)='pending'";
		return jdbcTemplate.query(SQL, new LeaveMgrMapper());
	}

	public void ApproveLeaves(List<Integer> leaveApproveIds) {

		String sql = "update leavestable set APPROVALSTATUS='Approved' where leaveid in(:ids)";
		namedParameterJdbcTemplate.update(sql, Collections.singletonMap("ids", leaveApproveIds));

	}

	public Profile getProfile(int empId) {
		String sql = "SELECT emp.EMPFIRSTNAME\r\n" + "  ||' '\r\n" + "  ||emp.EMPLASTNAME employeeName,\r\n"
				+ "  emp.EMPID employeeId,\r\n" + "  usr.USERNAME empUserName,\r\n"
				+ "  emp.EMPCELLNO employeeCell,\r\n" + "  emp.EMPEMAIL employeeEmail,\r\n"
				+ "  emp.EMPDOB employeeDOB,\r\n" + "  ad.ADDRESS1\r\n" + "  ||','\r\n" + "  ||ad.ADDRESS2\r\n"
				+ "  ||','\r\n" + "  ||ad.CITY\r\n" + "  ||','\r\n" + "  ||ad.STATE\r\n" + "  ||' '\r\n"
				+ "  ||ad.ZIPCODE employeeAddr,\r\n" + "  emp.EMPGENDER employeeGender,\r\n"
				+ "  emp.EMPSSN employeeSSN,\r\n" + "  mgr.EMPFIRSTNAME\r\n" + "  ||' '\r\n"
				+ "  ||mgr.EMPLASTNAME empManagerName,\r\n" + "  cat.CATEGORYDESC empLevel,\r\n"
				+ "  hlth.allergies allergies,\r\n" + "  emp.EMPVISATYPE employeeVisa\r\n"
				+ "FROM employeedetails emp\r\n" + "LEFT JOIN USERCREDENTIALS usr\r\n" + "ON emp.empid=usr.EMPID\r\n"
				+ "LEFT JOIN ADDRESSTABLE ad\r\n" + "ON ad.ADDRESSID=emp.EMPADDID\r\n"
				+ "LEFT JOIN EMPLOYEEDETAILS mgr\r\n" + "ON emp.EMPMANAGERID=mgr.empid\r\n"
				+ "LEFT JOIN CATEGORYTABLE cat\r\n" + "ON cat.EMPCATID=emp.EMPCATID\r\n"
				+ "LEFT JOIN EMPHEALTH hlth\r\n" + "ON hlth.empid=emp.empid \r\n" + "where emp.empid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { empId }, new ProfileMapper());

	}

	public void RejectLeaves(List<Integer> leaveIds) {

		String sql = "update leavestable set APPROVALSTATUS='Rejected' where leaveid in(:ids)";
		namedParameterJdbcTemplate.update(sql, Collections.singletonMap("ids", leaveIds));

	}

	public List<Training> getTrainingsDash(int empId) {
		String sql = " select ta.TRAININGDESC,tt.TRAININGASSIGNEDDATE, tt.STATUS from TRAININGSAVAILABLE ta join TRAININGSTRACKER tt on  tt.trainingid=ta.trainingid\r\n"
				+ "        where tt.empid=? and lower(tt.STATUS)='assigned'";
		return jdbcTemplate.query(sql, new Object[] { empId }, new TrainingMapper());

	}

	public List<Incentives> getIncentivesDash(int empId) {
		String SQL = "SELECT COUPONID,\r\n" + "  COUPONDESC,\r\n" + "  COUPONREDEEMEDSTATUS\r\n" + "FROM incentives\r\n"
				+ "WHERE empid=? and lower(COUPONREDEEMEDSTATUS)='not redeemed'";
		return jdbcTemplate.query(SQL, new Object[] { empId }, new IncentivesMapper());
	}

	public Integer getMgrLeavesPending() {
		String SQL = "select count(1) from leavestable where lower(APPROVALSTATUS)='pending'";
		return jdbcTemplate.queryForObject(SQL, new Object[] {}, Integer.class);
	}

	public Integer getAccessoriesPending() {
		String SQL = "select count(1) from ACCESSORYISSUE where lower(ACCESSORYISSUESTATUS)='not issued'";
		return jdbcTemplate.queryForObject(SQL, new Object[] {}, Integer.class);
	}

	public void updateAccessories(AccessoriesUpdate accessoriesUpdate) {
		String SQL = "update ACCESSORYISSUE set ACCESSORYISSUEDATE=TO_DATE(?, 'yyyy/mm/dd'), ACCESSORYISSUESTATUS=? where ACCESSORYID=? and EMPID=?";
		jdbcTemplate.update(SQL, new Object[] {
				accessoriesUpdate.getIssueDate(),accessoriesUpdate.getIssueStatus() ,
				accessoriesUpdate.getAccessoryId(),accessoriesUpdate.getEmployeeId()});

	}

	public void rate(String employeeId, String ratingPeriod, String rating, int raterId) {
		String SQL = "merge INTO EMPLOYEEPERFORMANCE ep USING\r\n" + 
				"(SELECT ? empid, ? emprating, ? ratingproviderid, ? ratingperiod FROM dual\r\n" + 
				")dl ON (dl.empid= ep.empid AND dl.RATINGPERIOD=ep.RATINGPERIOD)\r\n" + 
				"WHEN matched THEN\r\n" + 
				"  UPDATE\r\n" + 
				"  SET ep.RATINGPROVIDERID=dl.RATINGPROVIDERID,\r\n" + 
				"    ep.emprating         =dl.emprating WHEN NOT matched THEN\r\n" + 
				"  INSERT VALUES\r\n" + 
				"    (dl.EMPID, dl.EMPRATING, dl.RATINGPROVIDERID, dl.RATINGPERIOD\r\n" + 
				"    )";
		jdbcTemplate.update(SQL, new Object[] {employeeId,rating,raterId,ratingPeriod});
		
	}

}
