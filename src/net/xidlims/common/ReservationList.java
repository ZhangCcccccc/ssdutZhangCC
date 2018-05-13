package net.xidlims.common;

public class ReservationList {
	//预约id
	private int reservationId;
	private int reservationTag;
	//设备名称
	private String deviceName;
	//预约用户
	private String reserveUserName;
	//设备管理者
	private String deviceManagerName;
	//
	private String teacherName;
	private String reservationContent;
	//预约开始时间
	private String beginTime;
	//预约结束时间
	private String endTime;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getReservationTag() {
		return reservationTag;
	}

	public void setReservationTag(int reservationTag) {
		this.reservationTag = reservationTag;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getReserveUserName() {
		return reserveUserName;
	}

	public void setReserveUserName(String reserveUserName) {
		this.reserveUserName = reserveUserName;
	}

	public String getDeviceManagerName() {
		return deviceManagerName;
	}

	public void setDeviceManagerName(String deviceManagerName) {
		this.deviceManagerName = deviceManagerName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getReservationContent() {
		return reservationContent;
	}

	public void setReservationContent(String reservationContent) {
		this.reservationContent = reservationContent;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
