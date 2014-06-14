package common.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DartConstants {
	
	private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	public static final String OWNER = "owner";
	public static final String CUSTOMER = "customer";
	public static final String FALSEBOOL = "false";
	public static final String ORDERPLACED = "Placed";
	public static final String ORDERCANCELLED = "Cancelled";
	public static final String ORDERPENDINGPAYMENT = "Payment Pending";
	public static final String ORDERREADYDELIVERY = "Ready for Delivery";
	
	public static String getCurrentDate() {
		Date date = Calendar.getInstance().getTime();
		return DateFormatUtils.format(date, DEFAULT_DATE_FORMAT);
	}

}
