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

package mission_talend.exercice06_03_0_1;

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
 * Job: exercice06_03 Purpose: Création du job catastrophe<br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status TEST
 */
public class exercice06_03 implements TalendJob {

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
	private final String jobName = "exercice06_03";
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
					exercice06_03.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(exercice06_03.this, new Object[] { e, currentComponent, globalMap });
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

	public void tUniqRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFlowToIterate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
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

	public void tDBInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_1_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_1_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_1_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_1_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_1_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_1_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class outStruct implements routines.system.IPersistableRow<outStruct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public String DepartmentName;

		public String getDepartmentName() {
			return this.DepartmentName;
		}

		public Integer count;

		public Integer getCount() {
			return this.count;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

					this.DepartmentName = readString(dis);

					this.count = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

					this.DepartmentName = readString(dis);

					this.count = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

				// String

				writeString(this.DepartmentName, dos);

				// Integer

				writeInteger(this.count, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

				// String

				writeString(this.DepartmentName, dos);

				// Integer

				writeInteger(this.count, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",DepartmentName=" + DepartmentName);
			sb.append(",count=" + String.valueOf(count));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(outStruct other) {

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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public Integer count;

		public Integer getCount() {
			return this.count;
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

					this.count = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

					this.count = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

				// Integer

				writeInteger(this.count, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

				// Integer

				writeInteger(this.count, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",count=" + String.valueOf(count));
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

	public static class OnRowsEndStructtAggregateRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_1> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public Integer count;

		public Integer getCount() {
			return this.count;
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

					this.count = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

					this.count = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

				// Integer

				writeInteger(this.count, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

				// Integer

				writeInteger(this.count, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",count=" + String.valueOf(count));
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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public String PhoneNumber;

		public String getPhoneNumber() {
			return this.PhoneNumber;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Double Salary;

		public Double getSalary() {
			return this.Salary;
		}

		public Double CommissionPercentage;

		public Double getCommissionPercentage() {
			return this.CommissionPercentage;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Email=" + Email);
			sb.append(",PhoneNumber=" + PhoneNumber);
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",JobCode=" + JobCode);
			sb.append(",Salary=" + String.valueOf(Salary));
			sb.append(",CommissionPercentage=" + String.valueOf(CommissionPercentage));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
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

	public static class OnRowsEndStructtSortRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_1> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public String PhoneNumber;

		public String getPhoneNumber() {
			return this.PhoneNumber;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Double Salary;

		public Double getSalary() {
			return this.Salary;
		}

		public Double CommissionPercentage;

		public Double getCommissionPercentage() {
			return this.CommissionPercentage;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Email=" + Email);
			sb.append(",PhoneNumber=" + PhoneNumber);
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",JobCode=" + JobCode);
			sb.append(",Salary=" + String.valueOf(Salary));
			sb.append(",CommissionPercentage=" + String.valueOf(CommissionPercentage));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_1 other) {

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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public String PhoneNumber;

		public String getPhoneNumber() {
			return this.PhoneNumber;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Double Salary;

		public Double getSalary() {
			return this.Salary;
		}

		public Double CommissionPercentage;

		public Double getCommissionPercentage() {
			return this.CommissionPercentage;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Email=" + Email);
			sb.append(",PhoneNumber=" + PhoneNumber);
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",JobCode=" + JobCode);
			sb.append(",Salary=" + String.valueOf(Salary));
			sb.append(",CommissionPercentage=" + String.valueOf(CommissionPercentage));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

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
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public String PhoneNumber;

		public String getPhoneNumber() {
			return this.PhoneNumber;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Double Salary;

		public Double getSalary() {
			return this.Salary;
		}

		public Double CommissionPercentage;

		public Double getCommissionPercentage() {
			return this.CommissionPercentage;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Email=" + Email);
			sb.append(",PhoneNumber=" + PhoneNumber);
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",JobCode=" + JobCode);
			sb.append(",Salary=" + String.valueOf(Salary));
			sb.append(",CommissionPercentage=" + String.valueOf(CommissionPercentage));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public String PhoneNumber;

		public String getPhoneNumber() {
			return this.PhoneNumber;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Double Salary;

		public Double getSalary() {
			return this.Salary;
		}

		public Double CommissionPercentage;

		public Double getCommissionPercentage() {
			return this.CommissionPercentage;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Email=" + Email);
			sb.append(",PhoneNumber=" + PhoneNumber);
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",JobCode=" + JobCode);
			sb.append(",Salary=" + String.valueOf(Salary));
			sb.append(",CommissionPercentage=" + String.valueOf(CommissionPercentage));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
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
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];

		public Integer EmployeeID;

		public Integer getEmployeeID() {
			return this.EmployeeID;
		}

		public String FirstName;

		public String getFirstName() {
			return this.FirstName;
		}

		public String LastName;

		public String getLastName() {
			return this.LastName;
		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public String PhoneNumber;

		public String getPhoneNumber() {
			return this.PhoneNumber;
		}

		public java.util.Date HireDate;

		public java.util.Date getHireDate() {
			return this.HireDate;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Double Salary;

		public Double getSalary() {
			return this.Salary;
		}

		public Double CommissionPercentage;

		public Double getCommissionPercentage() {
			return this.CommissionPercentage;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice06_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice06_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice06_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice06_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice06_03, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.FirstName = readString(dis);

					this.LastName = readString(dis);

					this.Email = readString(dis);

					this.PhoneNumber = readString(dis);

					this.HireDate = readDate(dis);

					this.JobCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Salary = null;
					} else {
						this.Salary = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CommissionPercentage = null;
					} else {
						this.CommissionPercentage = dis.readDouble();
					}

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.EmployeeID, dos);

				// String

				writeString(this.FirstName, dos);

				// String

				writeString(this.LastName, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.PhoneNumber, dos);

				// java.util.Date

				writeDate(this.HireDate, dos);

				// String

				writeString(this.JobCode, dos);

				// Double

				if (this.Salary == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Salary);
				}

				// Double

				if (this.CommissionPercentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CommissionPercentage);
				}

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EmployeeID=" + String.valueOf(EmployeeID));
			sb.append(",FirstName=" + FirstName);
			sb.append(",LastName=" + LastName);
			sb.append(",Email=" + Email);
			sb.append(",PhoneNumber=" + PhoneNumber);
			sb.append(",HireDate=" + String.valueOf(HireDate));
			sb.append(",JobCode=" + JobCode);
			sb.append(",Salary=" + String.valueOf(Salary));
			sb.append(",CommissionPercentage=" + String.valueOf(CommissionPercentage));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartmentID=" + String.valueOf(DepartmentID));
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

				tDBInput_3Process(globalMap);

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				row3Struct row3 = new row3Struct();
				row4Struct row4 = new row4Struct();
				row5Struct row5 = new row5Struct();
				outStruct out = new outStruct();

				/**
				 * [tFlowToIterate_1 begin ] start
				 */

				int NB_ITERATE_tDBInput_2 = 0; // for statistics

				ok_Hash.put("tFlowToIterate_1", false);
				start_Hash.put("tFlowToIterate_1", System.currentTimeMillis());

				currentComponent = "tFlowToIterate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tFlowToIterate_1 = 0;

				int nb_line_tFlowToIterate_1 = 0;
				int counter_tFlowToIterate_1 = 0;

				/**
				 * [tFlowToIterate_1 begin ] stop
				 */

				/**
				 * [tUniqRow_1 begin ] start
				 */

				ok_Hash.put("tUniqRow_1", false);
				start_Hash.put("tUniqRow_1", System.currentTimeMillis());

				currentComponent = "tUniqRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tUniqRow_1 = 0;

				class KeyStruct_tUniqRow_1 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					Integer DepartmentID;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.DepartmentID == null) ? 0 : this.DepartmentID.hashCode());

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
						final KeyStruct_tUniqRow_1 other = (KeyStruct_tUniqRow_1) obj;

						if (this.DepartmentID == null) {
							if (other.DepartmentID != null)
								return false;

						} else if (!this.DepartmentID.equals(other.DepartmentID))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_1 = 0;
				int nb_duplicates_tUniqRow_1 = 0;
				KeyStruct_tUniqRow_1 finder_tUniqRow_1 = new KeyStruct_tUniqRow_1();
				java.util.Set<KeyStruct_tUniqRow_1> keystUniqRow_1 = new java.util.HashSet<KeyStruct_tUniqRow_1>();

				/**
				 * [tUniqRow_1 begin ] stop
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
						"enc:routine.encryption.key.v1:WB9xDIxDpJrgpuccIKnhSrtGF+IWK5Sl+6iDmCJVK/ScQI08FTrR");

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

				String dbquery_tDBInput_1 = "SELECT \n  `employees`.`EmployeeID`, \n  `employees`.`FirstName`, \n  `employees`.`LastName`, \n  `employees`.`Email`, \n  `"
						+ "employees`.`PhoneNumber`, \n  `employees`.`HireDate`, \n  `employees`.`JobCode`, \n  `employees`.`Salary`, \n  `employees`.`"
						+ "CommissionPercentage`, \n  `employees`.`ManagerID`, \n  `employees`.`DepartmentID`\nFROM `employees`";

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
							row1.FirstName = null;
						} else {

							row1.FirstName = routines.system.JDBCUtil.getString(rs_tDBInput_1, 2, false);
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.LastName = null;
						} else {

							row1.LastName = routines.system.JDBCUtil.getString(rs_tDBInput_1, 3, false);
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.Email = null;
						} else {

							row1.Email = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.PhoneNumber = null;
						} else {

							row1.PhoneNumber = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.HireDate = null;
						} else {

							if (rs_tDBInput_1.getString(6) != null) {
								String dateString_tDBInput_1 = rs_tDBInput_1.getString(6);
								if (!("0000-00-00").equals(dateString_tDBInput_1)
										&& !("0000-00-00 00:00:00").equals(dateString_tDBInput_1)) {
									row1.HireDate = rs_tDBInput_1.getTimestamp(6);
								} else {
									row1.HireDate = (java.util.Date) year0_tDBInput_1.clone();
								}
							} else {
								row1.HireDate = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.JobCode = null;
						} else {

							row1.JobCode = routines.system.JDBCUtil.getString(rs_tDBInput_1, 7, false);
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.Salary = null;
						} else {

							row1.Salary = rs_tDBInput_1.getDouble(8);
							if (rs_tDBInput_1.wasNull()) {
								row1.Salary = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.CommissionPercentage = null;
						} else {

							row1.CommissionPercentage = rs_tDBInput_1.getDouble(9);
							if (rs_tDBInput_1.wasNull()) {
								row1.CommissionPercentage = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.ManagerID = null;
						} else {

							row1.ManagerID = rs_tDBInput_1.getInt(10);
							if (rs_tDBInput_1.wasNull()) {
								row1.ManagerID = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.DepartmentID = null;
						} else {

							row1.DepartmentID = rs_tDBInput_1.getInt(11);
							if (rs_tDBInput_1.wasNull()) {
								row1.DepartmentID = null;
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
						 * [tUniqRow_1 main ] start
						 */

						currentComponent = "tUniqRow_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						row2 = null;
						finder_tUniqRow_1.DepartmentID = row1.DepartmentID;
						finder_tUniqRow_1.hashCodeDirty = true;
						if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
							KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

							new_tUniqRow_1.DepartmentID = row1.DepartmentID;

							keystUniqRow_1.add(new_tUniqRow_1);
							if (row2 == null) {

								row2 = new row2Struct();
							}
							row2.EmployeeID = row1.EmployeeID;
							row2.FirstName = row1.FirstName;
							row2.LastName = row1.LastName;
							row2.Email = row1.Email;
							row2.PhoneNumber = row1.PhoneNumber;
							row2.HireDate = row1.HireDate;
							row2.JobCode = row1.JobCode;
							row2.Salary = row1.Salary;
							row2.CommissionPercentage = row1.CommissionPercentage;
							row2.ManagerID = row1.ManagerID;
							row2.DepartmentID = row1.DepartmentID;
							nb_uniques_tUniqRow_1++;
						} else {
							nb_duplicates_tUniqRow_1++;
						}

						tos_count_tUniqRow_1++;

						/**
						 * [tUniqRow_1 main ] stop
						 */

						/**
						 * [tUniqRow_1 process_data_begin ] start
						 */

						currentComponent = "tUniqRow_1";

						/**
						 * [tUniqRow_1 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tFlowToIterate_1 main ] start
							 */

							currentComponent = "tFlowToIterate_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							globalMap.put("row2.EmployeeID", row2.EmployeeID);

							globalMap.put("row2.FirstName", row2.FirstName);

							globalMap.put("row2.LastName", row2.LastName);

							globalMap.put("row2.Email", row2.Email);

							globalMap.put("row2.PhoneNumber", row2.PhoneNumber);

							globalMap.put("row2.HireDate", row2.HireDate);

							globalMap.put("row2.JobCode", row2.JobCode);

							globalMap.put("row2.Salary", row2.Salary);

							globalMap.put("row2.CommissionPercentage", row2.CommissionPercentage);

							globalMap.put("row2.ManagerID", row2.ManagerID);

							globalMap.put("row2.DepartmentID", row2.DepartmentID);

							nb_line_tFlowToIterate_1++;
							counter_tFlowToIterate_1++;
							globalMap.put("tFlowToIterate_1_CURRENT_ITERATION", counter_tFlowToIterate_1);

							tos_count_tFlowToIterate_1++;

							/**
							 * [tFlowToIterate_1 main ] stop
							 */

							/**
							 * [tFlowToIterate_1 process_data_begin ] start
							 */

							currentComponent = "tFlowToIterate_1";

							/**
							 * [tFlowToIterate_1 process_data_begin ] stop
							 */
							NB_ITERATE_tDBInput_2++;

							if (execStat) {
								runStat.updateStatOnConnection("OnRowsEnd", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("row3", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("row4", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("out", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("row5", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("OnRowsEnd", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tDBInput_2);
								// Thread.sleep(1000);
							}

							/**
							 * [tSortRow_1_SortOut begin ] start
							 */

							ok_Hash.put("tSortRow_1_SortOut", false);
							start_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

							currentVirtualComponent = "tSortRow_1";

							currentComponent = "tSortRow_1_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
							}

							int tos_count_tSortRow_1_SortOut = 0;

							class Comparablerow3Struct extends row3Struct implements Comparable<Comparablerow3Struct> {

								public int compareTo(Comparablerow3Struct other) {

									if (this.DepartmentID == null && other.DepartmentID != null) {
										return -1;

									} else if (this.DepartmentID != null && other.DepartmentID == null) {
										return 1;

									} else if (this.DepartmentID != null && other.DepartmentID != null) {
										if (!this.DepartmentID.equals(other.DepartmentID)) {
											return this.DepartmentID.compareTo(other.DepartmentID);
										}
									}
									return 0;
								}
							}

							java.util.List<Comparablerow3Struct> list_tSortRow_1_SortOut = new java.util.ArrayList<Comparablerow3Struct>();

							/**
							 * [tSortRow_1_SortOut begin ] stop
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

							final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil
									.decryptPassword(
											"enc:routine.encryption.key.v1:CHIxGkJuxuohtqU9PCUp88I9gaGh8wVDYcpI4OhuYQkwk0pWeIMy");

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

							String dbquery_tDBInput_2 = "SELECT \n  `employees`.`EmployeeID`, \n  `employees`.`FirstName`, \n  `employees`.`LastName`, \n  `employees`.`Email`, \n  `"
									+ "employees`.`PhoneNumber`, \n  `employees`.`HireDate`, \n  `employees`.`JobCode`, \n  `employees`.`Salary`, \n  `employees`.`"
									+ "CommissionPercentage`, \n  `employees`.`ManagerID`, \n  `employees`.`DepartmentID`\nFROM `employees` WHERE\n`employees`.`De"
									+ "partmentID` =\n\"+((Integer)globalMap.get(\"row2.DepartmentID\"))";

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
										row3.EmployeeID = null;
									} else {

										row3.EmployeeID = rs_tDBInput_2.getInt(1);
										if (rs_tDBInput_2.wasNull()) {
											row3.EmployeeID = null;
										}
									}
									if (colQtyInRs_tDBInput_2 < 2) {
										row3.FirstName = null;
									} else {

										row3.FirstName = routines.system.JDBCUtil.getString(rs_tDBInput_2, 2, false);
									}
									if (colQtyInRs_tDBInput_2 < 3) {
										row3.LastName = null;
									} else {

										row3.LastName = routines.system.JDBCUtil.getString(rs_tDBInput_2, 3, false);
									}
									if (colQtyInRs_tDBInput_2 < 4) {
										row3.Email = null;
									} else {

										row3.Email = routines.system.JDBCUtil.getString(rs_tDBInput_2, 4, false);
									}
									if (colQtyInRs_tDBInput_2 < 5) {
										row3.PhoneNumber = null;
									} else {

										row3.PhoneNumber = routines.system.JDBCUtil.getString(rs_tDBInput_2, 5, false);
									}
									if (colQtyInRs_tDBInput_2 < 6) {
										row3.HireDate = null;
									} else {

										if (rs_tDBInput_2.getString(6) != null) {
											String dateString_tDBInput_2 = rs_tDBInput_2.getString(6);
											if (!("0000-00-00").equals(dateString_tDBInput_2)
													&& !("0000-00-00 00:00:00").equals(dateString_tDBInput_2)) {
												row3.HireDate = rs_tDBInput_2.getTimestamp(6);
											} else {
												row3.HireDate = (java.util.Date) year0_tDBInput_2.clone();
											}
										} else {
											row3.HireDate = null;
										}
									}
									if (colQtyInRs_tDBInput_2 < 7) {
										row3.JobCode = null;
									} else {

										row3.JobCode = routines.system.JDBCUtil.getString(rs_tDBInput_2, 7, false);
									}
									if (colQtyInRs_tDBInput_2 < 8) {
										row3.Salary = null;
									} else {

										row3.Salary = rs_tDBInput_2.getDouble(8);
										if (rs_tDBInput_2.wasNull()) {
											row3.Salary = null;
										}
									}
									if (colQtyInRs_tDBInput_2 < 9) {
										row3.CommissionPercentage = null;
									} else {

										row3.CommissionPercentage = rs_tDBInput_2.getDouble(9);
										if (rs_tDBInput_2.wasNull()) {
											row3.CommissionPercentage = null;
										}
									}
									if (colQtyInRs_tDBInput_2 < 10) {
										row3.ManagerID = null;
									} else {

										row3.ManagerID = rs_tDBInput_2.getInt(10);
										if (rs_tDBInput_2.wasNull()) {
											row3.ManagerID = null;
										}
									}
									if (colQtyInRs_tDBInput_2 < 11) {
										row3.DepartmentID = null;
									} else {

										row3.DepartmentID = rs_tDBInput_2.getInt(11);
										if (rs_tDBInput_2.wasNull()) {
											row3.DepartmentID = null;
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
									 * [tSortRow_1_SortOut main ] start
									 */

									currentVirtualComponent = "tSortRow_1";

									currentComponent = "tSortRow_1_SortOut";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row3"

										);
									}

									Comparablerow3Struct arrayRowtSortRow_1_SortOut = new Comparablerow3Struct();

									arrayRowtSortRow_1_SortOut.EmployeeID = row3.EmployeeID;
									arrayRowtSortRow_1_SortOut.FirstName = row3.FirstName;
									arrayRowtSortRow_1_SortOut.LastName = row3.LastName;
									arrayRowtSortRow_1_SortOut.Email = row3.Email;
									arrayRowtSortRow_1_SortOut.PhoneNumber = row3.PhoneNumber;
									arrayRowtSortRow_1_SortOut.HireDate = row3.HireDate;
									arrayRowtSortRow_1_SortOut.JobCode = row3.JobCode;
									arrayRowtSortRow_1_SortOut.Salary = row3.Salary;
									arrayRowtSortRow_1_SortOut.CommissionPercentage = row3.CommissionPercentage;
									arrayRowtSortRow_1_SortOut.ManagerID = row3.ManagerID;
									arrayRowtSortRow_1_SortOut.DepartmentID = row3.DepartmentID;
									list_tSortRow_1_SortOut.add(arrayRowtSortRow_1_SortOut);

									tos_count_tSortRow_1_SortOut++;

									/**
									 * [tSortRow_1_SortOut main ] stop
									 */

									/**
									 * [tSortRow_1_SortOut process_data_begin ] start
									 */

									currentVirtualComponent = "tSortRow_1";

									currentComponent = "tSortRow_1_SortOut";

									/**
									 * [tSortRow_1_SortOut process_data_begin ] stop
									 */

									/**
									 * [tSortRow_1_SortOut process_data_end ] start
									 */

									currentVirtualComponent = "tSortRow_1";

									currentComponent = "tSortRow_1_SortOut";

									/**
									 * [tSortRow_1_SortOut process_data_end ] stop
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
							 * [tSortRow_1_SortOut end ] start
							 */

							currentVirtualComponent = "tSortRow_1";

							currentComponent = "tSortRow_1_SortOut";

							row3Struct[] array_tSortRow_1_SortOut = list_tSortRow_1_SortOut
									.toArray(new Comparablerow3Struct[0]);

							java.util.Arrays.sort(array_tSortRow_1_SortOut);

							globalMap.put("tSortRow_1", array_tSortRow_1_SortOut);

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
							}

							ok_Hash.put("tSortRow_1_SortOut", true);
							end_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

							/**
							 * [tSortRow_1_SortOut end ] stop
							 */

							/**
							 * [tAggregateRow_1_AGGOUT begin ] start
							 */

							ok_Hash.put("tAggregateRow_1_AGGOUT", false);
							start_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

							currentVirtualComponent = "tAggregateRow_1";

							currentComponent = "tAggregateRow_1_AGGOUT";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
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
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'short/Short'", "'byte/Byte'"));
									}
								}

								public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
									short r = (short) (a + b);
									if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'int/Integer'", "'short/Short'"));
									}
								}

								public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
									int r = a + b;
									if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'long/Long'", "'int/Integer'"));
									}
								}

								public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
									long r = a + b;
									if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'BigDecimal'", "'long/Long'"));
									}
								}

								public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

									if (checkUlp) {
										float minAddedValue = Math.ulp(a);
										if (minAddedValue > Math.abs(b)) {
											throw new RuntimeException(buildPrecisionMessage(String.valueOf(a),
													String.valueOf(b), "'double' or 'BigDecimal'", "'float/Float'"));
										}
									}

									if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
											|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'double' or 'BigDecimal'", "'float/Float'"));
									}
								}

								public void checkedIADD(double a, double b, boolean checkTypeOverFlow,
										boolean checkUlp) {

									if (checkUlp) {
										double minAddedValue = Math.ulp(a);
										if (minAddedValue > Math.abs(b)) {
											throw new RuntimeException(buildPrecisionMessage(String.valueOf(a),
													String.valueOf(a), "'BigDecimal'", "'double/Double'"));
										}
									}

									if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE)
											|| (a + b < -Double.MAX_VALUE)) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'BigDecimal'", "'double/Double'"));
									}
								}

								public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

									if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE)
											|| (a + b < -Double.MAX_VALUE)) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'BigDecimal'", "'double/Double'"));
									}
								}

								public void checkedIADD(double a, short b, boolean checkTypeOverFlow,
										boolean checkUlp) {

									if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE)
											|| (a + b < -Double.MAX_VALUE)) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'BigDecimal'", "'double/Double'"));
									}
								}

								public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

									if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE)
											|| (a + b < -Double.MAX_VALUE)) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'BigDecimal'", "'double/Double'"));
									}
								}

								public void checkedIADD(double a, float b, boolean checkTypeOverFlow,
										boolean checkUlp) {

									if (checkUlp) {
										double minAddedValue = Math.ulp(a);
										if (minAddedValue > Math.abs(b)) {
											throw new RuntimeException(buildPrecisionMessage(String.valueOf(a),
													String.valueOf(a), "'BigDecimal'", "'double/Double'"));
										}
									}

									if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE)
											|| (a + b < -Double.MAX_VALUE)) {
										throw new RuntimeException(buildOverflowMessage(String.valueOf(a),
												String.valueOf(b), "'BigDecimal'", "'double/Double'"));
									}
								}

								private String buildOverflowMessage(String a, String b, String advicedTypes,
										String originalType) {
									return "Type overflow when adding " + b + " to " + a
											+ ", to resolve this problem, increase the precision by using "
											+ advicedTypes + " type in place of " + originalType + ".";
								}

								private String buildPrecisionMessage(String a, String b, String advicedTypes,
										String originalType) {
									return "The double precision is unsufficient to add the value " + b + " to " + a
											+ ", to resolve this problem, increase the precision by using "
											+ advicedTypes + " type in place of " + originalType + ".";
								}

							} // G_OutBegin_AggR_144

							UtilClass_tAggregateRow_1 utilClass_tAggregateRow_1 = new UtilClass_tAggregateRow_1();

							class AggOperationStruct_tAggregateRow_1 { // G_OutBegin_AggR_100

								private static final int DEFAULT_HASHCODE = 1;
								private static final int PRIME = 31;
								private int hashCode = DEFAULT_HASHCODE;
								public boolean hashCodeDirty = true;

								Integer DepartmentID;
								int count = 0;
								int count_clmCount = 0;

								@Override
								public int hashCode() {
									if (this.hashCodeDirty) {
										final int prime = PRIME;
										int result = DEFAULT_HASHCODE;

										result = prime * result
												+ ((this.DepartmentID == null) ? 0 : this.DepartmentID.hashCode());

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

									if (this.DepartmentID == null) {
										if (other.DepartmentID != null)
											return false;
									} else if (!this.DepartmentID.equals(other.DepartmentID))
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
							 * [tSortRow_1_SortIn begin ] start
							 */

							ok_Hash.put("tSortRow_1_SortIn", false);
							start_Hash.put("tSortRow_1_SortIn", System.currentTimeMillis());

							currentVirtualComponent = "tSortRow_1";

							currentComponent = "tSortRow_1_SortIn";

							int tos_count_tSortRow_1_SortIn = 0;

							row3Struct[] array_tSortRow_1_SortIn = (row3Struct[]) globalMap.remove("tSortRow_1");

							int nb_line_tSortRow_1_SortIn = 0;

							row3Struct current_tSortRow_1_SortIn = null;

							for (int i_tSortRow_1_SortIn = 0; i_tSortRow_1_SortIn < array_tSortRow_1_SortIn.length; i_tSortRow_1_SortIn++) {
								current_tSortRow_1_SortIn = array_tSortRow_1_SortIn[i_tSortRow_1_SortIn];
								row4.EmployeeID = current_tSortRow_1_SortIn.EmployeeID;
								row4.FirstName = current_tSortRow_1_SortIn.FirstName;
								row4.LastName = current_tSortRow_1_SortIn.LastName;
								row4.Email = current_tSortRow_1_SortIn.Email;
								row4.PhoneNumber = current_tSortRow_1_SortIn.PhoneNumber;
								row4.HireDate = current_tSortRow_1_SortIn.HireDate;
								row4.JobCode = current_tSortRow_1_SortIn.JobCode;
								row4.Salary = current_tSortRow_1_SortIn.Salary;
								row4.CommissionPercentage = current_tSortRow_1_SortIn.CommissionPercentage;
								row4.ManagerID = current_tSortRow_1_SortIn.ManagerID;
								row4.DepartmentID = current_tSortRow_1_SortIn.DepartmentID;
								// increase number of line sorted
								nb_line_tSortRow_1_SortIn++;

								/**
								 * [tSortRow_1_SortIn begin ] stop
								 */

								/**
								 * [tSortRow_1_SortIn main ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortIn";

								tos_count_tSortRow_1_SortIn++;

								/**
								 * [tSortRow_1_SortIn main ] stop
								 */

								/**
								 * [tSortRow_1_SortIn process_data_begin ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortIn";

								/**
								 * [tSortRow_1_SortIn process_data_begin ] stop
								 */

								/**
								 * [tAggregateRow_1_AGGOUT main ] start
								 */

								currentVirtualComponent = "tAggregateRow_1";

								currentComponent = "tAggregateRow_1_AGGOUT";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

								operation_finder_tAggregateRow_1.DepartmentID = row4.DepartmentID;

								operation_finder_tAggregateRow_1.hashCodeDirty = true;

								operation_result_tAggregateRow_1 = hash_tAggregateRow_1
										.get(operation_finder_tAggregateRow_1);

								if (operation_result_tAggregateRow_1 == null) { // G_OutMain_AggR_001

									operation_result_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();

									operation_result_tAggregateRow_1.DepartmentID = operation_finder_tAggregateRow_1.DepartmentID;

									hash_tAggregateRow_1.put(operation_result_tAggregateRow_1,
											operation_result_tAggregateRow_1);

								} // G_OutMain_AggR_001

								operation_result_tAggregateRow_1.count_clmCount++;
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
								 * [tSortRow_1_SortIn process_data_end ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortIn";

								/**
								 * [tSortRow_1_SortIn process_data_end ] stop
								 */

								/**
								 * [tSortRow_1_SortIn end ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortIn";

							}

							globalMap.put("tSortRow_1_SortIn_NB_LINE", nb_line_tSortRow_1_SortIn);

							ok_Hash.put("tSortRow_1_SortIn", true);
							end_Hash.put("tSortRow_1_SortIn", System.currentTimeMillis());

							/**
							 * [tSortRow_1_SortIn end ] stop
							 */

							/**
							 * [tAggregateRow_1_AGGOUT end ] start
							 */

							currentVirtualComponent = "tAggregateRow_1";

							currentComponent = "tAggregateRow_1_AGGOUT";

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
							}

							ok_Hash.put("tAggregateRow_1_AGGOUT", true);
							end_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

							/**
							 * [tAggregateRow_1_AGGOUT end ] stop
							 */

							/**
							 * [tFileOutputDelimited_1 begin ] start
							 */

							ok_Hash.put("tFileOutputDelimited_1", false);
							start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

							currentComponent = "tFileOutputDelimited_1";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out");
							}

							int tos_count_tFileOutputDelimited_1 = 0;

							String fileName_tFileOutputDelimited_1 = "";
							fileName_tFileOutputDelimited_1 = (new java.io.File(
									"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/catastrophe_out.csv"))
											.getAbsolutePath().replace("\\", "/");
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

							final String OUT_DELIM_tFileOutputDelimited_1 = /**
																			 * Start field
																			 * tFileOutputDelimited_1:FIELDSEPARATOR
																			 */
									";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
							;

							final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																					 * Start field
																					 * tFileOutputDelimited_1:ROWSEPARATOR
																					 */
									"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
							;

							// create directory only if not exists
							if (directory_tFileOutputDelimited_1 != null
									&& directory_tFileOutputDelimited_1.trim().length() != 0) {
								java.io.File dir_tFileOutputDelimited_1 = new java.io.File(
										directory_tFileOutputDelimited_1);
								if (!dir_tFileOutputDelimited_1.exists()) {
									dir_tFileOutputDelimited_1.mkdirs();
								}
							}

							// routines.system.Row
							java.io.Writer outtFileOutputDelimited_1 = null;

							java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(
									fileName_tFileOutputDelimited_1);
							if (fileToDelete_tFileOutputDelimited_1.exists()) {
								fileToDelete_tFileOutputDelimited_1.delete();
							}
							outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
									new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false),
									"ISO-8859-15"));

							resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
							resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

							/**
							 * [tFileOutputDelimited_1 begin ] stop
							 */

							/**
							 * [tMap_1 begin ] start
							 */

							ok_Hash.put("tMap_1", false);
							start_Hash.put("tMap_1", System.currentTimeMillis());

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
							}

							int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

							org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) globalMap
									.get("tHash_Lookup_row6"));

							row6Struct row6HashKey = new row6Struct();
							row6Struct row6Default = new row6Struct();
// ###############################        

// ###############################
// # Vars initialization
							class Var__tMap_1__Struct {
							}
							Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
							outStruct out_tmp = new outStruct();
// ###############################

							/**
							 * [tMap_1 begin ] stop
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

								row5.DepartmentID = aggregated_row_tAggregateRow_1.DepartmentID;
								row5.count = (int) aggregated_row_tAggregateRow_1.count;
								row5.count = (int) aggregated_row_tAggregateRow_1.count_clmCount;

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
								 * [tMap_1 main ] start
								 */

								currentComponent = "tMap_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row5"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_1 = false;
								boolean mainRowRejected_tMap_1 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "row6"
								///////////////////////////////////////////////

								boolean forceLooprow6 = false;

								row6Struct row6ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_1 = false;

									row6HashKey.DepartmentID = row5.DepartmentID;

									row6HashKey.hashCodeDirty = true;

									tHash_Lookup_row6.lookup(row6HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row6 != null && tHash_Lookup_row6.getCount(row6HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row6'
									// and it contains more one result from keys : row6.DepartmentID = '" +
									// row6HashKey.DepartmentID + "'");
								} // G 071

								row6Struct row6 = null;

								row6Struct fromLookup_row6 = null;
								row6 = row6Default;

								if (tHash_Lookup_row6 != null && tHash_Lookup_row6.hasNext()) { // G 099

									fromLookup_row6 = tHash_Lookup_row6.next();

								} // G 099

								if (fromLookup_row6 != null) {
									row6 = fromLookup_row6;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									out = null;

// # Output table : 'out'
									out_tmp.DepartmentID = row5.DepartmentID;
									out_tmp.DepartmentName = row6.DepartmentName;
									out_tmp.count = row5.count;
									out = out_tmp;
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
// Start of branch "out"
								if (out != null) {

									/**
									 * [tFileOutputDelimited_1 main ] start
									 */

									currentComponent = "tFileOutputDelimited_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "out"

										);
									}

									StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
									if (out.DepartmentID != null) {
										sb_tFileOutputDelimited_1.append(out.DepartmentID);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (out.DepartmentName != null) {
										sb_tFileOutputDelimited_1.append(out.DepartmentName);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (out.count != null) {
										sb_tFileOutputDelimited_1.append(out.count);
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

								} // End of branch "out"

								/**
								 * [tMap_1 process_data_end ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_end ] stop
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
							 * [tMap_1 end ] start
							 */

							currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
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
								runStat.updateStat(resourceMap, iterateId, 2, 0, "out");
							}

							ok_Hash.put("tFileOutputDelimited_1", true);
							end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

							/**
							 * [tFileOutputDelimited_1 end ] stop
							 */

							if (execStat) {
								runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tDBInput_2);
							}

							/**
							 * [tFlowToIterate_1 process_data_end ] start
							 */

							currentComponent = "tFlowToIterate_1";

							/**
							 * [tFlowToIterate_1 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tUniqRow_1 process_data_end ] start
						 */

						currentComponent = "tUniqRow_1";

						/**
						 * [tUniqRow_1 process_data_end ] stop
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
				 * [tUniqRow_1 end ] start
				 */

				currentComponent = "tUniqRow_1";

				globalMap.put("tUniqRow_1_NB_UNIQUES", nb_uniques_tUniqRow_1);
				globalMap.put("tUniqRow_1_NB_DUPLICATES", nb_duplicates_tUniqRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tUniqRow_1", true);
				end_Hash.put("tUniqRow_1", System.currentTimeMillis());

				/**
				 * [tUniqRow_1 end ] stop
				 */

				/**
				 * [tFlowToIterate_1 end ] start
				 */

				currentComponent = "tFlowToIterate_1";

				globalMap.put("tFlowToIterate_1_NB_LINE", nb_line_tFlowToIterate_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tFlowToIterate_1", true);
				end_Hash.put("tFlowToIterate_1", System.currentTimeMillis());

				/**
				 * [tFlowToIterate_1 end ] stop
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

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row6");

			// free memory for "tAggregateRow_1_AGGIN"
			globalMap.remove("tAggregateRow_1");

			// free memory for "tSortRow_1_SortIn"
			globalMap.remove("tSortRow_1");

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tUniqRow_1 finally ] start
				 */

				currentComponent = "tUniqRow_1";

				/**
				 * [tUniqRow_1 finally ] stop
				 */

				/**
				 * [tFlowToIterate_1 finally ] start
				 */

				currentComponent = "tFlowToIterate_1";

				/**
				 * [tFlowToIterate_1 finally ] stop
				 */

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tSortRow_1_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				/**
				 * [tSortRow_1_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_1_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortIn";

				/**
				 * [tSortRow_1_SortIn finally ] stop
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

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableComparableLookupRow<row6Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice06_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice06_03 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DepartmentID;

		public Integer getDepartmentID() {
			return this.DepartmentID;
		}

		public String DepartmentName;

		public String getDepartmentName() {
			return this.DepartmentName;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer LocationID;

		public Integer getLocationID() {
			return this.LocationID;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DepartmentID == null) ? 0 : this.DepartmentID.hashCode());

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
			final row6Struct other = (row6Struct) obj;

			if (this.DepartmentID == null) {
				if (other.DepartmentID != null)
					return false;

			} else if (!this.DepartmentID.equals(other.DepartmentID))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.DepartmentID = this.DepartmentID;
			other.DepartmentName = this.DepartmentName;
			other.ManagerID = this.ManagerID;
			other.LocationID = this.LocationID;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.DepartmentID = this.DepartmentID;

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

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice06_03) {

				try {

					int length = 0;

					this.DepartmentID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DepartmentID, dos);

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

				this.DepartmentName = readString(dis, ois);

				this.ManagerID = readInteger(dis, ois);

				this.LocationID = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DepartmentName = readString(dis, objectIn);

				this.ManagerID = readInteger(dis, objectIn);

				this.LocationID = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.DepartmentName, dos, oos);

				writeInteger(this.ManagerID, dos, oos);

				writeInteger(this.LocationID, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.DepartmentName, dos, objectOut);

				writeInteger(this.ManagerID, dos, objectOut);

				writeInteger(this.LocationID, dos, objectOut);

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
			sb.append("DepartmentID=" + String.valueOf(DepartmentID));
			sb.append(",DepartmentName=" + DepartmentName);
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",LocationID=" + String.valueOf(LocationID));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DepartmentID, other.DepartmentID);
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

	public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

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

				row6Struct row6 = new row6Struct();

				/**
				 * [tAdvancedHash_row6 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row6", false);
				start_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tAdvancedHash_row6 = 0;

				// connection name:row6
				// source node:tDBInput_3 - inputs:(after_tDBInput_1) outputs:(row6,row6) |
				// target node:tAdvancedHash_row6 - inputs:(row6) outputs:()
				// linked node: tMap_1 - inputs:(row5,row6) outputs:(out)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row6Struct>getLookup(matchingModeEnum_row6);

				globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);

				/**
				 * [tAdvancedHash_row6 begin ] stop
				 */

				/**
				 * [tDBInput_3 begin ] start
				 */

				ok_Hash.put("tDBInput_3", false);
				start_Hash.put("tDBInput_3", System.currentTimeMillis());

				currentComponent = "tDBInput_3";

				int tos_count_tDBInput_3 = 0;

				java.util.Calendar calendar_tDBInput_3 = java.util.Calendar.getInstance();
				calendar_tDBInput_3.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_3 = calendar_tDBInput_3.getTime();
				int nb_line_tDBInput_3 = 0;
				java.sql.Connection conn_tDBInput_3 = null;
				String driverClass_tDBInput_3 = "com.mysql.cj.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_3 = java.lang.Class.forName(driverClass_tDBInput_3);
				String dbUser_tDBInput_3 = "talend";

				final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:pfcPH+FH8uXgs1wk2yKT/YXUFw3e3O+1TBnrwBHskVVShsOEzMVi");

				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;

				String properties_tDBInput_3 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBInput_3 == null || properties_tDBInput_3.trim().length() == 0) {
					properties_tDBInput_3 = "";
				}
				String url_tDBInput_3 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "hr" + "?"
						+ properties_tDBInput_3;

				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3, dbUser_tDBInput_3,
						dbPwd_tDBInput_3);

				java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

				String dbquery_tDBInput_3 = "SELECT \n  `departments`.`DepartmentID`, \n  `departments`.`DepartmentName`, \n  `departments`.`ManagerID`, \n  `department"
						+ "s`.`LocationID`\nFROM `departments`";

				globalMap.put("tDBInput_3_QUERY", dbquery_tDBInput_3);
				java.sql.ResultSet rs_tDBInput_3 = null;

				try {
					rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
					java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
					int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

					String tmpContent_tDBInput_3 = null;

					while (rs_tDBInput_3.next()) {
						nb_line_tDBInput_3++;

						if (colQtyInRs_tDBInput_3 < 1) {
							row6.DepartmentID = null;
						} else {

							row6.DepartmentID = rs_tDBInput_3.getInt(1);
							if (rs_tDBInput_3.wasNull()) {
								row6.DepartmentID = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 2) {
							row6.DepartmentName = null;
						} else {

							row6.DepartmentName = routines.system.JDBCUtil.getString(rs_tDBInput_3, 2, false);
						}
						if (colQtyInRs_tDBInput_3 < 3) {
							row6.ManagerID = null;
						} else {

							row6.ManagerID = rs_tDBInput_3.getInt(3);
							if (rs_tDBInput_3.wasNull()) {
								row6.ManagerID = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 4) {
							row6.LocationID = null;
						} else {

							row6.LocationID = rs_tDBInput_3.getInt(4);
							if (rs_tDBInput_3.wasNull()) {
								row6.LocationID = null;
							}
						}

						/**
						 * [tDBInput_3 begin ] stop
						 */

						/**
						 * [tDBInput_3 main ] start
						 */

						currentComponent = "tDBInput_3";

						tos_count_tDBInput_3++;

						/**
						 * [tDBInput_3 main ] stop
						 */

						/**
						 * [tDBInput_3 process_data_begin ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row6 main ] start
						 */

						currentComponent = "tAdvancedHash_row6";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row6"

							);
						}

						row6Struct row6_HashRow = new row6Struct();

						row6_HashRow.DepartmentID = row6.DepartmentID;

						row6_HashRow.DepartmentName = row6.DepartmentName;

						row6_HashRow.ManagerID = row6.ManagerID;

						row6_HashRow.LocationID = row6.LocationID;

						tHash_Lookup_row6.put(row6_HashRow);

						tos_count_tAdvancedHash_row6++;

						/**
						 * [tAdvancedHash_row6 main ] stop
						 */

						/**
						 * [tAdvancedHash_row6 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row6";

						/**
						 * [tAdvancedHash_row6 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row6 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row6";

						/**
						 * [tAdvancedHash_row6 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 process_data_end ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 end ] start
						 */

						currentComponent = "tDBInput_3";

					}
				} finally {
					if (rs_tDBInput_3 != null) {
						rs_tDBInput_3.close();
					}
					if (stmt_tDBInput_3 != null) {
						stmt_tDBInput_3.close();
					}
					if (conn_tDBInput_3 != null && !conn_tDBInput_3.isClosed()) {

						conn_tDBInput_3.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_3_NB_LINE", nb_line_tDBInput_3);

				ok_Hash.put("tDBInput_3", true);
				end_Hash.put("tDBInput_3", System.currentTimeMillis());

				/**
				 * [tDBInput_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row6 end ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				tHash_Lookup_row6.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tAdvancedHash_row6", true);
				end_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row6 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_3 finally ] start
				 */

				currentComponent = "tDBInput_3";

				/**
				 * [tDBInput_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row6 finally ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				/**
				 * [tAdvancedHash_row6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
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
		final exercice06_03 exercice06_03Class = new exercice06_03();

		int exitCode = exercice06_03Class.runJobInTOS(args);

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
			java.io.InputStream inContext = exercice06_03.class.getClassLoader()
					.getResourceAsStream("mission_talend/exercice06_03_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = exercice06_03.class.getClassLoader()
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
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : exercice06_03");
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
 * 195697 characters generated by Talend Open Studio for Data Integration on the
 * 10 février 2023 à 12:03:27 GMT+01:00
 ************************************************************************************************/