package kr.co.college.bean;

public class CollegeBean {
	
	// lecture
	private int lecNo;
	private String lecName;
	private int lecCredit;
	private int lecTime;
	private String lecClass;
	
	// register
	private int regStdNo; 
	private int	regLecNo;
	private int	regMidScore;
	private int regFinalScore;
	private int	regTotalScore;
	private String regGrade;
	
	// student
	private int stdNo;
	private String stName;
	private String stdHp;
	private int stdYear;
	private String stdAddress;
	
	
	
	// lecture
	public int getLecNo() {
		return lecNo;
	}
	public void setLecNo(int lecNo) {
		this.lecNo = lecNo;
	}
	public void setLecNo(String lecNo) {
		this.lecNo = Integer.parseInt(lecNo);
	}
	
	public String getLecName() {
		return lecName;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	
	public int getLecCredit() {
		return lecCredit;
	}
	public void setLecCredit(int lecCredit) {
		this.lecCredit = lecCredit;
	}
	public void setLecCredit(String lecCredit) {
		this.lecCredit = Integer.parseInt(lecCredit);
	}
	
	public int getLecTime() {
		return lecTime;
	}
	public void setLecTime(int lecTime) {
		this.lecTime = lecTime;
	}
	public void setLecTime(String lecTime) {
		this.lecTime = Integer.parseInt(lecTime);
	}
	
	public String getLecClass() {
		return lecClass;
	}
	public void setLecClass(String lecClass) {
		this.lecClass = lecClass;
	}
	
	// register
	public int getRegStdNo() {
		return regStdNo;
	}
	public void setRegStdNo(int regStdNo) {
		this.regStdNo = regStdNo;
	}
	public int getRegLecNo() {
		return regLecNo;
	}
	public void setRegLecNo(int regLecNo) {
		this.regLecNo = regLecNo;
	}
	public int getRegMidScore() {
		return regMidScore;
	}
	public void setRegMidScore(int regMidScore) {
		this.regMidScore = regMidScore;
	}
	public int getRegFinalScore() {
		return regFinalScore;
	}
	public void setRegFinalScore(int regFinalScore) {
		this.regFinalScore = regFinalScore;
	}
	public int getRegTotalScore() {
		return regTotalScore;
	}
	public void setRegTotalScore(int regTotalScore) {
		this.regTotalScore = regTotalScore;
	}
	public String getRegGrade() {
		return regGrade;
	}
	public void setRegGrade(String regGrade) {
		this.regGrade = regGrade;
	}
	
	
	// student
	
	public int getStdNo() {
		return stdNo;
	}
	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStdHp() {
		return stdHp;
	}
	public void setStdHp(String stdHp) {
		this.stdHp = stdHp;
	}
	public int getStdYear() {
		return stdYear;
	}
	public void setStdYear(int stdYear) {
		this.stdYear = stdYear;
	}
	public String getStdAddress() {
		return stdAddress;
	}
	public void setStdAddress(String stdAddress) {
		this.stdAddress = stdAddress;
	}
	
}
