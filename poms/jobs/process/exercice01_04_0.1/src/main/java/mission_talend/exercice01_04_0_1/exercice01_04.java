// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package mission_talend.exercice01_04_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: exercice01_04 Purpose: Définitions des managers aux nouveaux recrus<br>
 * Description: Définir les managers pour chaque groupe de recrus selon le
 * nombre d'employés et/ou les années d'expériences. <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status TEST
 */
public class exercice01_04 implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "exercice01_04";
	private final String projectName = "MISSION_TALEND";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					exercice01_04.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(exercice01_04.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterColumns_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_1_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_1_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_1_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public Integer nbEmployees;

		public Integer getNbEmployees() {
			return this.nbEmployees;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",nbEmployees=" + String.valueOf(nbEmployees));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public Integer nbEmployees;

		public Integer getNbEmployees() {
			return this.nbEmployees;
		}

		public Long annciente;

		public Long getAnnciente() {
			return this.annciente;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.annciente = null;
					} else {
						this.annciente = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.annciente = null;
					} else {
						this.annciente = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

				// Long

				if (this.annciente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.annciente);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

				// Long

				if (this.annciente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.annciente);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",nbEmployees=" + String.valueOf(nbEmployees));
			sb.append(",annciente=" + String.valueOf(annciente));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public Integer nbEmployees;

		public Integer getNbEmployees() {
			return this.nbEmployees;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",nbEmployees=" + String.valueOf(nbEmployees));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class rejectStruct implements routines.system.IPersistableRow<rejectStruct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public Integer nbEmployees;

		public Integer getNbEmployees() {
			return this.nbEmployees;
		}

		public Long annciente;

		public Long getAnnciente() {
			return this.annciente;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.annciente = null;
					} else {
						this.annciente = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.annciente = null;
					} else {
						this.annciente = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

				// Long

				if (this.annciente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.annciente);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

				// Long

				if (this.annciente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.annciente);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",nbEmployees=" + String.valueOf(nbEmployees));
			sb.append(",annciente=" + String.valueOf(annciente));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(rejectStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tDBInput_1Struct implements routines.system.IPersistableRow<after_tDBInput_1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tDBInput_1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_2Process(globalMap);

				row1Struct row1 = new row1Struct();
				out1Struct out1 = new out1Struct();
				rejectStruct reject = new rejectStruct();
				row4Struct row4 = new row4Struct();
				row5Struct row5 = new row5Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/manager.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_2", false);
				start_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tFileOutputDelimited_2 = 0;

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File(
						"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/managers.csv")).getAbsolutePath()
								.replace("\\", "/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
							fileName_tFileOutputDelimited_2.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);
				int nb_line_tFileOutputDelimited_2 = 0;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /** Start field tFileOutputDelimited_2:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
																		 * Start field
																		 * tFileOutputDelimited_2:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null && directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {
						dir_tFileOutputDelimited_2.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;

				java.io.File fileToDelete_tFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				if (fileToDelete_tFileOutputDelimited_2.exists()) {
					fileToDelete_tFileOutputDelimited_2.delete();
				}
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_2, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_2.length() == 0) {
					outtFileOutputDelimited_2.write("EmployeeID");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("ManagerID");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("DepartmentID");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("HireDate");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("nbEmployees");
					outtFileOutputDelimited_2.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_2", outtFileOutputDelimited_2);
				resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tFilterColumns_1 begin ] start
				 */

				ok_Hash.put("tFilterColumns_1", false);
				start_Hash.put("tFilterColumns_1", System.currentTimeMillis());

				currentComponent = "tFilterColumns_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tFilterColumns_1 = 0;

				int nb_line_tFilterColumns_1 = 0;

				/**
				 * [tFilterColumns_1 begin ] stop
				 */

				/**
				 * [tFilterRow_1 begin ] start
				 */

				ok_Hash.put("tFilterRow_1", false);
				start_Hash.put("tFilterRow_1", System.currentTimeMillis());

				currentComponent = "tFilterRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "reject");
				}

				int tos_count_tFilterRow_1 = 0;

				int nb_line_tFilterRow_1 = 0;
				int nb_line_ok_tFilterRow_1 = 0;
				int nb_line_reject_tFilterRow_1 = 0;

				class Operator_tFilterRow_1 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_1(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
						.get("tHash_Lookup_row3"));

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out1Struct out1_tmp = new out1Struct();
				rejectStruct reject_tmp = new rejectStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				java.util.Calendar calendar_tDBInput_1 = java.util.Calendar.getInstance();
				calendar_tDBInput_1.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_1 = calendar_tDBInput_1.getTime();
				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "com.mysql.cj.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "talend";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:3zwkSzpHXmY9bjazqIz1Q69Z28o5YuWI/RuW2zpU27cK7PNRlKs/");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String properties_tDBInput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBInput_1 == null || properties_tDBInput_1.trim().length() == 0) {
					properties_tDBInput_1 = "";
				}
				String url_tDBInput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "hr" + "?"
						+ properties_tDBInput_1;

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT \n  `employees`.`EmployeeID`, \n  `employees`.`ManagerID`,\n  `employees`.`DepartmentID`,\n  `employees`.`HireDate"
						+ "`\nFROM `employees`";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row1.EmployeeID = null;
						} else {

							row1.EmployeeID = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								row1.EmployeeID = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.ManagerID = null;
						} else {

							row1.ManagerID = rs_tDBInput_1.getInt(2);
							if (rs_tDBInput_1.wasNull()) {
								row1.ManagerID = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.DepartmentID = null;
						} else {

							row1.DepartmentID = rs_tDBInput_1.getInt(3);
							if (rs_tDBInput_1.wasNull()) {
								row1.DepartmentID = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.HireDate = null;
						} else {

							if (rs_tDBInput_1.getString(4) != null) {
								String dateString_tDBInput_1 = rs_tDBInput_1.getString(4);
								if (!("0000-00-00").equals(dateString_tDBInput_1)
										&& !("0000-00-00 00:00:00").equals(dateString_tDBInput_1)) {
									row1.HireDate = rs_tDBInput_1.getTimestamp(4);
								} else {
									row1.HireDate = (java.util.Date) year0_tDBInput_1.clone();
								}
							} else {
								row1.HireDate = null;
							}
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "row3"
						///////////////////////////////////////////////

						boolean forceLooprow3 = false;

						row3Struct row3ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_1 = false;

							row3HashKey.ManagerID = row1.EmployeeID;

							row3HashKey.hashCodeDirty = true;

							tHash_Lookup_row3.lookup(row3HashKey);

							if (!tHash_Lookup_row3.hasNext()) { // G_TM_M_090

								rejectedInnerJoin_tMap_1 = true;

							} // G_TM_M_090

						} // G_TM_M_020

						if (tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071

							// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3'
							// and it contains more one result from keys : row3.ManagerID = '" +
							// row3HashKey.ManagerID + "'");
						} // G 071

						row3Struct row3 = null;

						row3Struct fromLookup_row3 = null;
						row3 = row3Default;

						if (tHash_Lookup_row3 != null && tHash_Lookup_row3.hasNext()) { // G 099

							fromLookup_row3 = tHash_Lookup_row3.next();

						} // G 099

						if (fromLookup_row3 != null) {
							row3 = fromLookup_row3;
						}

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							out1 = null;
							reject = null;

							if (!rejectedInnerJoin_tMap_1) {

// # Output table : 'out1'
								out1_tmp.EmployeeID = row1.EmployeeID;
								out1_tmp.ManagerID = row1.ManagerID;
								out1_tmp.DepartmentID = row1.DepartmentID;
								out1_tmp.HireDate = row1.HireDate;
								out1_tmp.nbEmployees = row3.nbEmployees;
								out1 = out1_tmp;

// # Output table : 'reject'
								reject_tmp.EmployeeID = row1.EmployeeID;
								reject_tmp.ManagerID = row1.ManagerID;
								reject_tmp.DepartmentID = row1.DepartmentID;
								reject_tmp.HireDate = row1.HireDate;
								reject_tmp.nbEmployees = row3.nbEmployees;
								reject_tmp.annciente = TalendDate.diffDate(new Date(), row1.HireDate, "yyyy");
								reject = reject_tmp;
							} // closing inner join bracket (2)
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_1 = false;

						tos_count_tMap_1++;

						/**
						 * [tMap_1 main ] stop
						 */

						/**
						 * [tMap_1 process_data_begin ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_begin ] stop
						 */
// Start of branch "out1"
						if (out1 != null) {

							/**
							 * [tFileOutputDelimited_1 main ] start
							 */

							currentComponent = "tFileOutputDelimited_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "out1"

								);
							}

							StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
							if (out1.EmployeeID != null) {
								sb_tFileOutputDelimited_1.append(out1.EmployeeID);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (out1.ManagerID != null) {
								sb_tFileOutputDelimited_1.append(out1.ManagerID);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (out1.DepartmentID != null) {
								sb_tFileOutputDelimited_1.append(out1.DepartmentID);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (out1.HireDate != null) {
								sb_tFileOutputDelimited_1
										.append(FormatterUtils.format_Date(out1.HireDate, "dd-MM-yyyy"));
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (out1.nbEmployees != null) {
								sb_tFileOutputDelimited_1.append(out1.nbEmployees);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

							nb_line_tFileOutputDelimited_1++;
							resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

							outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

							tos_count_tFileOutputDelimited_1++;

							/**
							 * [tFileOutputDelimited_1 main ] stop
							 */

							/**
							 * [tFileOutputDelimited_1 process_data_begin ] start
							 */

							currentComponent = "tFileOutputDelimited_1";

							/**
							 * [tFileOutputDelimited_1 process_data_begin ] stop
							 */

							/**
							 * [tFileOutputDelimited_1 process_data_end ] start
							 */

							currentComponent = "tFileOutputDelimited_1";

							/**
							 * [tFileOutputDelimited_1 process_data_end ] stop
							 */

						} // End of branch "out1"

// Start of branch "reject"
						if (reject != null) {

							/**
							 * [tFilterRow_1 main ] start
							 */

							currentComponent = "tFilterRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "reject"

								);
							}

							row4 = null;
							Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
							ope_tFilterRow_1
									.matches(
											(reject.annciente == null ? false
													: reject.annciente.compareTo(
															ParserUtils.parseTo_Long(String.valueOf(20))) >= 0),
											"annciente.compareTo(20) >= 0 failed");

							if (ope_tFilterRow_1.getMatchFlag()) {
								if (row4 == null) {
									row4 = new row4Struct();
								}
								row4.EmployeeID = reject.EmployeeID;
								row4.ManagerID = reject.ManagerID;
								row4.DepartmentID = reject.DepartmentID;
								row4.HireDate = reject.HireDate;
								row4.nbEmployees = reject.nbEmployees;
								row4.annciente = reject.annciente;
								nb_line_ok_tFilterRow_1++;
							} else {
								nb_line_reject_tFilterRow_1++;
							}

							nb_line_tFilterRow_1++;

							tos_count_tFilterRow_1++;

							/**
							 * [tFilterRow_1 main ] stop
							 */

							/**
							 * [tFilterRow_1 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_begin ] stop
							 */
// Start of branch "row4"
							if (row4 != null) {

								/**
								 * [tFilterColumns_1 main ] start
								 */

								currentComponent = "tFilterColumns_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

								row5.EmployeeID = row4.EmployeeID;

								row5.ManagerID = row4.ManagerID;

								row5.DepartmentID = row4.DepartmentID;

								row5.HireDate = row4.HireDate;

								row5.nbEmployees = row4.nbEmployees;

								nb_line_tFilterColumns_1++;

								tos_count_tFilterColumns_1++;

								/**
								 * [tFilterColumns_1 main ] stop
								 */

								/**
								 * [tFilterColumns_1 process_data_begin ] start
								 */

								currentComponent = "tFilterColumns_1";

								/**
								 * [tFilterColumns_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 main ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row5"

									);
								}

								StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();
								if (row5.EmployeeID != null) {
									sb_tFileOutputDelimited_2.append(row5.EmployeeID);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (row5.ManagerID != null) {
									sb_tFileOutputDelimited_2.append(row5.ManagerID);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (row5.DepartmentID != null) {
									sb_tFileOutputDelimited_2.append(row5.DepartmentID);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (row5.HireDate != null) {
									sb_tFileOutputDelimited_2
											.append(FormatterUtils.format_Date(row5.HireDate, "dd-MM-yyyy"));
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (row5.nbEmployees != null) {
									sb_tFileOutputDelimited_2.append(row5.nbEmployees);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

								nb_line_tFileOutputDelimited_2++;
								resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

								outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2.toString());

								tos_count_tFileOutputDelimited_2++;

								/**
								 * [tFileOutputDelimited_2 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								/**
								 * [tFileOutputDelimited_2 process_data_end ] stop
								 */

								/**
								 * [tFilterColumns_1 process_data_end ] start
								 */

								currentComponent = "tFilterColumns_1";

								/**
								 * [tFilterColumns_1 process_data_end ] stop
								 */

							} // End of branch "row4"

							/**
							 * [tFilterRow_1 process_data_end ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_end ] stop
							 */

						} // End of branch "reject"

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tFilterRow_1 end ] start
				 */

				currentComponent = "tFilterRow_1";

				globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "reject");
				}

				ok_Hash.put("tFilterRow_1", true);
				end_Hash.put("tFilterRow_1", System.currentTimeMillis());

				/**
				 * [tFilterRow_1 end ] stop
				 */

				/**
				 * [tFilterColumns_1 end ] start
				 */

				currentComponent = "tFilterColumns_1";

				globalMap.put("tFilterColumns_1_NB_LINE", nb_line_tFilterColumns_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFilterColumns_1", true);
				end_Hash.put("tFilterColumns_1", System.currentTimeMillis());

				/**
				 * [tFilterColumns_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 end ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE", nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row3");

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tFilterRow_1 finally ] start
				 */

				currentComponent = "tFilterRow_1";

				/**
				 * [tFilterRow_1 finally ] stop
				 */

				/**
				 * [tFilterColumns_1 finally ] start
				 */

				currentComponent = "tFilterColumns_1";

				/**
				 * [tFilterColumns_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer nbEmployees;

		public Integer getNbEmployees() {
			return this.nbEmployees;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ManagerID == null) ? 0 : this.ManagerID.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.ManagerID == null) {
				if (other.ManagerID != null)
					return false;

			} else if (!this.ManagerID.equals(other.ManagerID))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.ManagerID = this.ManagerID;
			other.nbEmployees = this.nbEmployees;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.ManagerID = this.ManagerID;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.ManagerID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.ManagerID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ManagerID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ManagerID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.nbEmployees = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.nbEmployees = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.nbEmployees, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.nbEmployees, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ManagerID=" + String.valueOf(ManagerID));
			sb.append(",nbEmployees=" + String.valueOf(nbEmployees));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ManagerID, other.ManagerID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtAggregateRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_1> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer nbEmployees;

		public Integer getNbEmployees() {
			return this.nbEmployees;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.ManagerID = readInteger(dis);

					this.nbEmployees = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.ManagerID = readInteger(dis);

					this.nbEmployees = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.nbEmployees, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ManagerID=" + String.valueOf(ManagerID));
			sb.append(",nbEmployees=" + String.valueOf(nbEmployees));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_1 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice01_04 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice01_04 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice01_04) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] start
				 */

				ok_Hash.put("tAggregateRow_1_AGGOUT", false);
				start_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tAggregateRow_1_AGGOUT = 0;

// ------------ Seems it is not used

				java.util.Map hashAggreg_tAggregateRow_1 = new java.util.HashMap();

// ------------

				class UtilClass_tAggregateRow_1 { // G_OutBegin_AggR_144

					public double sd(Double[] data) {
						final int n = data.length;
						if (n < 2) {
							return Double.NaN;
						}
						double d1 = 0d;
						double d2 = 0d;

						for (int i = 0; i < data.length; i++) {
							d1 += (data[i] * data[i]);
							d2 += data[i];
						}

						return Math.sqrt((n * d1 - d2 * d2) / n / (n - 1));
					}

					public void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {
						byte r = (byte) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'short/Short'", "'byte/Byte'"));
						}
					}

					public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
						short r = (short) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'int/Integer'", "'short/Short'"));
						}
					}

					public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
						int r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'long/Long'", "'int/Integer'"));
						}
					}

					public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
						long r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'long/Long'"));
						}
					}

					public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							float minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b),
										"'double' or 'BigDecimal'", "'float/Float'"));
							}
						}

						if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
								|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'double' or 'BigDecimal'", "'float/Float'"));
						}
					}

					public void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, short b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					private String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {
						return "Type overflow when adding " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

					private String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {
						return "The double precision is unsufficient to add the value " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

				} // G_OutBegin_AggR_144

				UtilClass_tAggregateRow_1 utilClass_tAggregateRow_1 = new UtilClass_tAggregateRow_1();

				class AggOperationStruct_tAggregateRow_1 { // G_OutBegin_AggR_100

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					Integer ManagerID;
					int count = 0;
					int nbEmployees_clmCount = 0;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.ManagerID == null) ? 0 : this.ManagerID.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final AggOperationStruct_tAggregateRow_1 other = (AggOperationStruct_tAggregateRow_1) obj;

						if (this.ManagerID == null) {
							if (other.ManagerID != null)
								return false;
						} else if (!this.ManagerID.equals(other.ManagerID))
							return false;

						return true;
					}

				} // G_OutBegin_AggR_100

				AggOperationStruct_tAggregateRow_1 operation_result_tAggregateRow_1 = null;
				AggOperationStruct_tAggregateRow_1 operation_finder_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();
				java.util.Map<AggOperationStruct_tAggregateRow_1, AggOperationStruct_tAggregateRow_1> hash_tAggregateRow_1 = new java.util.HashMap<AggOperationStruct_tAggregateRow_1, AggOperationStruct_tAggregateRow_1>();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				java.util.Calendar calendar_tDBInput_2 = java.util.Calendar.getInstance();
				calendar_tDBInput_2.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_2 = calendar_tDBInput_2.getTime();
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.mysql.cj.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "talend";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:2BLy4bo/7rpzIApvyMnXmoqTpUSw294msEeoRQgfsyp1j5DXde9U");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String properties_tDBInput_2 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBInput_2 == null || properties_tDBInput_2.trim().length() == 0) {
					properties_tDBInput_2 = "";
				}
				String url_tDBInput_2 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "hr" + "?"
						+ properties_tDBInput_2;

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT \n  `employees`.`EmployeeID`, \n  `employees`.`ManagerID`\nFROM `employees`";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row2.EmployeeID = null;
						} else {

							row2.EmployeeID = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								row2.EmployeeID = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row2.ManagerID = null;
						} else {

							row2.ManagerID = rs_tDBInput_2.getInt(2);
							if (rs_tDBInput_2.wasNull()) {
								row2.ManagerID = null;
							}
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tAggregateRow_1_AGGOUT main ] start
						 */

						currentVirtualComponent = "tAggregateRow_1";

						currentComponent = "tAggregateRow_1_AGGOUT";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

							);
						}

						operation_finder_tAggregateRow_1.ManagerID = row2.ManagerID;

						operation_finder_tAggregateRow_1.hashCodeDirty = true;

						operation_result_tAggregateRow_1 = hash_tAggregateRow_1.get(operation_finder_tAggregateRow_1);

						if (operation_result_tAggregateRow_1 == null) { // G_OutMain_AggR_001

							operation_result_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();

							operation_result_tAggregateRow_1.ManagerID = operation_finder_tAggregateRow_1.ManagerID;

							hash_tAggregateRow_1.put(operation_result_tAggregateRow_1,
									operation_result_tAggregateRow_1);

						} // G_OutMain_AggR_001

						operation_result_tAggregateRow_1.nbEmployees_clmCount++;
						operation_result_tAggregateRow_1.count++;

						tos_count_tAggregateRow_1_AGGOUT++;

						/**
						 * [tAggregateRow_1_AGGOUT main ] stop
						 */

						/**
						 * [tAggregateRow_1_AGGOUT process_data_begin ] start
						 */

						currentVirtualComponent = "tAggregateRow_1";

						currentComponent = "tAggregateRow_1_AGGOUT";

						/**
						 * [tAggregateRow_1_AGGOUT process_data_begin ] stop
						 */

						/**
						 * [tAggregateRow_1_AGGOUT process_data_end ] start
						 */

						currentVirtualComponent = "tAggregateRow_1";

						currentComponent = "tAggregateRow_1_AGGOUT";

						/**
						 * [tAggregateRow_1_AGGOUT process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tAggregateRow_1_AGGOUT", true);
				end_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

				/**
				 * [tAggregateRow_1_AGGOUT end ] stop
				 */

				/**
				 * [tAdvancedHash_row3 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row3", false);
				start_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tAdvancedHash_row3 = 0;

				// connection name:row3
				// source node:tAggregateRow_1_AGGIN - inputs:(OnRowsEnd) outputs:(row3,row3) |
				// target node:tAdvancedHash_row3 - inputs:(row3) outputs:()
				// linked node: tMap_1 - inputs:(row1,row3) outputs:(out1,reject)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct>getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGIN begin ] start
				 */

				ok_Hash.put("tAggregateRow_1_AGGIN", false);
				start_Hash.put("tAggregateRow_1_AGGIN", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGIN";

				int tos_count_tAggregateRow_1_AGGIN = 0;

				java.util.Collection<AggOperationStruct_tAggregateRow_1> values_tAggregateRow_1 = hash_tAggregateRow_1
						.values();

				globalMap.put("tAggregateRow_1_NB_LINE", values_tAggregateRow_1.size());

				for (AggOperationStruct_tAggregateRow_1 aggregated_row_tAggregateRow_1 : values_tAggregateRow_1) { // G_AggR_600

					/**
					 * [tAggregateRow_1_AGGIN begin ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN main ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

					row3.ManagerID = aggregated_row_tAggregateRow_1.ManagerID;
					row3.nbEmployees = (int) aggregated_row_tAggregateRow_1.count;
					row3.nbEmployees = (int) aggregated_row_tAggregateRow_1.nbEmployees_clmCount;

					tos_count_tAggregateRow_1_AGGIN++;

					/**
					 * [tAggregateRow_1_AGGIN main ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

					/**
					 * [tAggregateRow_1_AGGIN process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row3 main ] start
					 */

					currentComponent = "tAdvancedHash_row3";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row3"

						);
					}

					row3Struct row3_HashRow = new row3Struct();

					row3_HashRow.ManagerID = row3.ManagerID;

					row3_HashRow.nbEmployees = row3.nbEmployees;

					tHash_Lookup_row3.put(row3_HashRow);

					tos_count_tAdvancedHash_row3++;

					/**
					 * [tAdvancedHash_row3 main ] stop
					 */

					/**
					 * [tAdvancedHash_row3 process_data_begin ] start
					 */

					currentComponent = "tAdvancedHash_row3";

					/**
					 * [tAdvancedHash_row3 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row3 process_data_end ] start
					 */

					currentComponent = "tAdvancedHash_row3";

					/**
					 * [tAdvancedHash_row3 process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

					/**
					 * [tAggregateRow_1_AGGIN process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

				} // G_AggR_600

				ok_Hash.put("tAggregateRow_1_AGGIN", true);
				end_Hash.put("tAggregateRow_1_AGGIN", System.currentTimeMillis());

				/**
				 * [tAggregateRow_1_AGGIN end ] stop
				 */

				/**
				 * [tAdvancedHash_row3 end ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				tHash_Lookup_row3.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tAdvancedHash_row3", true);
				end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tAggregateRow_1_AGGIN"
			globalMap.remove("tAggregateRow_1");

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				/**
				 * [tAggregateRow_1_AGGOUT finally ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGIN finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGIN";

				/**
				 * [tAggregateRow_1_AGGIN finally ] stop
				 */

				/**
				 * [tAdvancedHash_row3 finally ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				/**
				 * [tAdvancedHash_row3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final exercice01_04 exercice01_04Class = new exercice01_04();

		int exitCode = exercice01_04Class.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = exercice01_04.class.getClassLoader()
					.getResourceAsStream("mission_talend/exercice01_04_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = exercice01_04.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tDBInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_1) {
			globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

			e_tDBInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : exercice01_04");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 144738 characters generated by Talend Open Studio for Data Integration on the
 * 6 février 2023 à 16:36:56 GMT+01:00
 ************************************************************************************************/