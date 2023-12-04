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

package mission_talend.excercice01_05_0_1;

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
 * Job: excercice01_05 Purpose: Affectation des candidats aux managers et aux
 * départements<br>
 * Description: Affecter les candidats aux managers et aux départements selon la
 * définition <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status TEST
 */
public class excercice01_05 implements TalendJob {

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
	private final String jobName = "excercice01_05";
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
					excercice01_05.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(excercice01_05.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSetGlobalVar_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSetGlobalVar_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFlowToIterate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSampleRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_1_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_1_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_1_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tSetGlobalVar_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tHashInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

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

				row1Struct row1 = new row1Struct();

				/**
				 * [tHashOutput_1 begin ] start
				 */

				ok_Hash.put("tHashOutput_1", false);
				start_Hash.put("tHashOutput_1", System.currentTimeMillis());

				currentComponent = "tHashOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tHashOutput_1 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row1Struct> tHashFile_tHashOutput_1 = null;
				String hashKey_tHashOutput_1 = "tHashFile_excercice01_05_" + pid + "_tHashOutput_1";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_1)) {
					if (mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1) == null) {
						mf_tHashOutput_1.getResourceMap().put(hashKey_tHashOutput_1,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row1Struct>(
										org.talend.designer.components.hashfile.common.MATCHING_MODE.KEEP_ALL));
						tHashFile_tHashOutput_1 = mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1);
					} else {
						tHashFile_tHashOutput_1 = mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1);
					}
				}
				int nb_line_tHashOutput_1 = 0;

				/**
				 * [tHashOutput_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Users/Abdelhak Pedro/Documents/Talend_Mission/managers.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/managers.csv", "ISO-8859-15", ";",
								"\n", true, 0, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.EmployeeID = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"EmployeeID", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.EmployeeID = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.ManagerID = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ManagerID", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.ManagerID = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.DepartmentID = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DepartmentID", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.DepartmentID = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.HireDate = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"HireDate", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.HireDate = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 4;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.nbEmployees = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"nbEmployees", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.nbEmployees = null;

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tHashOutput_1 main ] start
							 */

							currentComponent = "tHashOutput_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row1Struct oneRow_tHashOutput_1 = new row1Struct();

							oneRow_tHashOutput_1.EmployeeID = row1.EmployeeID;
							oneRow_tHashOutput_1.ManagerID = row1.ManagerID;
							oneRow_tHashOutput_1.DepartmentID = row1.DepartmentID;
							oneRow_tHashOutput_1.HireDate = row1.HireDate;
							oneRow_tHashOutput_1.nbEmployees = row1.nbEmployees;

							tHashFile_tHashOutput_1.put(oneRow_tHashOutput_1);
							nb_line_tHashOutput_1++;

							tos_count_tHashOutput_1++;

							/**
							 * [tHashOutput_1 main ] stop
							 */

							/**
							 * [tHashOutput_1 process_data_begin ] start
							 */

							currentComponent = "tHashOutput_1";

							/**
							 * [tHashOutput_1 process_data_begin ] stop
							 */

							/**
							 * [tHashOutput_1 process_data_end ] start
							 */

							currentComponent = "tHashOutput_1";

							/**
							 * [tHashOutput_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Users/Abdelhak Pedro/Documents/Talend_Mission/managers.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tHashOutput_1 end ] start
				 */

				currentComponent = "tHashOutput_1";

				globalMap.put("tHashOutput_1_NB_LINE", nb_line_tHashOutput_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tHashOutput_1", true);
				end_Hash.put("tHashOutput_1", System.currentTimeMillis());

				/**
				 * [tHashOutput_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tFileInputDelimited_2Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tHashOutput_1 finally ] start
				 */

				currentComponent = "tHashOutput_1";

				/**
				 * [tHashOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public java.util.Date datedenaissance;

		public java.util.Date getDatedenaissance() {
			return this.datedenaissance;
		}

		public Integer salairedemande;

		public Integer getSalairedemande() {
			return this.salairedemande;
		}

		public Integer numTel;

		public Integer getNumTel() {
			return this.numTel;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Integer MinSalary;

		public Integer getMinSalary() {
			return this.MinSalary;
		}

		public Integer MaxSalary;

		public Integer getMaxSalary() {
			return this.MaxSalary;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",datedenaissance=" + String.valueOf(datedenaissance));
			sb.append(",salairedemande=" + String.valueOf(salairedemande));
			sb.append(",numTel=" + String.valueOf(numTel));
			sb.append(",JobCode=" + JobCode);
			sb.append(",MinSalary=" + String.valueOf(MinSalary));
			sb.append(",MaxSalary=" + String.valueOf(MaxSalary));
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

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

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

				row2Struct row2 = new row2Struct();

				/**
				 * [tHashOutput_2 begin ] start
				 */

				ok_Hash.put("tHashOutput_2", false);
				start_Hash.put("tHashOutput_2", System.currentTimeMillis());

				currentComponent = "tHashOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tHashOutput_2 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_2 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row2Struct> tHashFile_tHashOutput_2 = null;
				String hashKey_tHashOutput_2 = "tHashFile_excercice01_05_" + pid + "_tHashOutput_2";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_2)) {
					if (mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2) == null) {
						mf_tHashOutput_2.getResourceMap().put(hashKey_tHashOutput_2,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row2Struct>(
										org.talend.designer.components.hashfile.common.MATCHING_MODE.KEEP_ALL));
						tHashFile_tHashOutput_2 = mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2);
					} else {
						tHashFile_tHashOutput_2 = mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2);
					}
				}
				int nb_line_tHashOutput_2 = 0;

				/**
				 * [tHashOutput_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/Users/Abdelhak Pedro/Documents/Talend_Mission/nouveaux_recrus.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/nouveaux_recrus.csv", "ISO-8859-15",
								";", "\n", true, 0, 0, limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row2 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							row2.nom = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 1;

							row2.prenom = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 2;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.datedenaissance = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"datedenaissance", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.datedenaissance = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 3;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.salairedemande = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"salairedemande", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.salairedemande = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 4;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.numTel = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"numTel", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.numTel = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 5;

							row2.JobCode = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 6;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.MinSalary = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MinSalary", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.MinSalary = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 7;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.MaxSalary = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MaxSalary", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.MaxSalary = null;

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row2 = null;

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tHashOutput_2 main ] start
							 */

							currentComponent = "tHashOutput_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							row2Struct oneRow_tHashOutput_2 = new row2Struct();

							oneRow_tHashOutput_2.nom = row2.nom;
							oneRow_tHashOutput_2.prenom = row2.prenom;
							oneRow_tHashOutput_2.datedenaissance = row2.datedenaissance;
							oneRow_tHashOutput_2.salairedemande = row2.salairedemande;
							oneRow_tHashOutput_2.numTel = row2.numTel;
							oneRow_tHashOutput_2.JobCode = row2.JobCode;
							oneRow_tHashOutput_2.MinSalary = row2.MinSalary;
							oneRow_tHashOutput_2.MaxSalary = row2.MaxSalary;

							tHashFile_tHashOutput_2.put(oneRow_tHashOutput_2);
							nb_line_tHashOutput_2++;

							tos_count_tHashOutput_2++;

							/**
							 * [tHashOutput_2 main ] stop
							 */

							/**
							 * [tHashOutput_2 process_data_begin ] start
							 */

							currentComponent = "tHashOutput_2";

							/**
							 * [tHashOutput_2 process_data_begin ] stop
							 */

							/**
							 * [tHashOutput_2 process_data_end ] start
							 */

							currentComponent = "tHashOutput_2";

							/**
							 * [tHashOutput_2 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/Users/Abdelhak Pedro/Documents/Talend_Mission/nouveaux_recrus.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tHashOutput_2 end ] start
				 */

				currentComponent = "tHashOutput_2";

				globalMap.put("tHashOutput_2_NB_LINE", nb_line_tHashOutput_2);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tHashOutput_2", true);
				end_Hash.put("tHashOutput_2", System.currentTimeMillis());

				/**
				 * [tHashOutput_2 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_2:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
			}

			tSetGlobalVar_1Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tHashOutput_2 finally ] start
				 */

				currentComponent = "tHashOutput_2";

				/**
				 * [tHashOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public void tSetGlobalVar_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tSetGlobalVar_1_SUBPROCESS_STATE", 0);

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

				/**
				 * [tSetGlobalVar_1 begin ] start
				 */

				ok_Hash.put("tSetGlobalVar_1", false);
				start_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());

				currentComponent = "tSetGlobalVar_1";

				int tos_count_tSetGlobalVar_1 = 0;

				/**
				 * [tSetGlobalVar_1 begin ] stop
				 */

				/**
				 * [tSetGlobalVar_1 main ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				globalMap.put("x", ((Integer) globalMap.get("tFileInputDelimited_2_NB_LINE"))
						/ ((Integer) globalMap.get("tFileInputDelimited_1_NB_LINE")));

				tos_count_tSetGlobalVar_1++;

				/**
				 * [tSetGlobalVar_1 main ] stop
				 */

				/**
				 * [tSetGlobalVar_1 process_data_begin ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				/**
				 * [tSetGlobalVar_1 process_data_begin ] stop
				 */

				/**
				 * [tSetGlobalVar_1 process_data_end ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				/**
				 * [tSetGlobalVar_1 process_data_end ] stop
				 */

				/**
				 * [tSetGlobalVar_1 end ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				ok_Hash.put("tSetGlobalVar_1", true);
				end_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());

				/**
				 * [tSetGlobalVar_1 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tSetGlobalVar_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk3", 0, "ok");
			}

			tHashInput_1Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tSetGlobalVar_1 finally ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				/**
				 * [tSetGlobalVar_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tSetGlobalVar_1_SUBPROCESS_STATE", 1);
	}

	public static class affectStruct implements routines.system.IPersistableRow<affectStruct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public java.util.Date datedenaissance;

		public java.util.Date getDatedenaissance() {
			return this.datedenaissance;
		}

		public Integer salairedemande;

		public Integer getSalairedemande() {
			return this.salairedemande;
		}

		public Integer numTel;

		public Integer getNumTel() {
			return this.numTel;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Integer MinSalary;

		public Integer getMinSalary() {
			return this.MinSalary;
		}

		public Integer MaxSalary;

		public Integer getMaxSalary() {
			return this.MaxSalary;
		}

		public Integer ManagerID;

		public Integer getManagerID() {
			return this.ManagerID;
		}

		public Integer DepartementID;

		public Integer getDepartementID() {
			return this.DepartementID;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartementID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartementID = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartementID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

				// Integer

				writeInteger(this.ManagerID, dos);

				// Integer

				writeInteger(this.DepartementID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",datedenaissance=" + String.valueOf(datedenaissance));
			sb.append(",salairedemande=" + String.valueOf(salairedemande));
			sb.append(",numTel=" + String.valueOf(numTel));
			sb.append(",JobCode=" + JobCode);
			sb.append(",MinSalary=" + String.valueOf(MinSalary));
			sb.append(",MaxSalary=" + String.valueOf(MaxSalary));
			sb.append(",ManagerID=" + String.valueOf(ManagerID));
			sb.append(",DepartementID=" + String.valueOf(DepartementID));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(affectStruct other) {

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

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public java.util.Date datedenaissance;

		public java.util.Date getDatedenaissance() {
			return this.datedenaissance;
		}

		public Integer salairedemande;

		public Integer getSalairedemande() {
			return this.salairedemande;
		}

		public Integer numTel;

		public Integer getNumTel() {
			return this.numTel;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Integer MinSalary;

		public Integer getMinSalary() {
			return this.MinSalary;
		}

		public Integer MaxSalary;

		public Integer getMaxSalary() {
			return this.MaxSalary;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",datedenaissance=" + String.valueOf(datedenaissance));
			sb.append(",salairedemande=" + String.valueOf(salairedemande));
			sb.append(",numTel=" + String.valueOf(numTel));
			sb.append(",JobCode=" + JobCode);
			sb.append(",MinSalary=" + String.valueOf(MinSalary));
			sb.append(",MaxSalary=" + String.valueOf(MaxSalary));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

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

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public java.util.Date datedenaissance;

		public java.util.Date getDatedenaissance() {
			return this.datedenaissance;
		}

		public Integer salairedemande;

		public Integer getSalairedemande() {
			return this.salairedemande;
		}

		public Integer numTel;

		public Integer getNumTel() {
			return this.numTel;
		}

		public String JobCode;

		public String getJobCode() {
			return this.JobCode;
		}

		public Integer MinSalary;

		public Integer getMinSalary() {
			return this.MinSalary;
		}

		public Integer MaxSalary;

		public Integer getMaxSalary() {
			return this.MaxSalary;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

					this.JobCode = readString(dis);

					this.MinSalary = readInteger(dis);

					this.MaxSalary = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// java.util.Date

				writeDate(this.datedenaissance, dos);

				// Integer

				writeInteger(this.salairedemande, dos);

				// Integer

				writeInteger(this.numTel, dos);

				// String

				writeString(this.JobCode, dos);

				// Integer

				writeInteger(this.MinSalary, dos);

				// Integer

				writeInteger(this.MaxSalary, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",datedenaissance=" + String.valueOf(datedenaissance));
			sb.append(",salairedemande=" + String.valueOf(salairedemande));
			sb.append(",numTel=" + String.valueOf(numTel));
			sb.append(",JobCode=" + JobCode);
			sb.append(",MinSalary=" + String.valueOf(MinSalary));
			sb.append(",MaxSalary=" + String.valueOf(MaxSalary));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

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
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

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

		public String sample;

		public String getSample() {
			return this.sample;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					this.sample = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					this.sample = readString(dis);

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

				// String

				writeString(this.sample, dos);

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

				// String

				writeString(this.sample, dos);

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
			sb.append(",sample=" + sample);
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

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

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

		public String sample;

		public String getSample() {
			return this.sample;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_excercice01_05.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_excercice01_05.length == 0) {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_excercice01_05 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_excercice01_05, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_excercice01_05, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					this.sample = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

				try {

					int length = 0;

					this.EmployeeID = readInteger(dis);

					this.ManagerID = readInteger(dis);

					this.DepartmentID = readInteger(dis);

					this.HireDate = readDate(dis);

					this.nbEmployees = readInteger(dis);

					this.sample = readString(dis);

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

				// String

				writeString(this.sample, dos);

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

				// String

				writeString(this.sample, dos);

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
			sb.append(",sample=" + sample);
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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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
		final static byte[] commonByteArrayLock_MISSION_TALEND_excercice01_05 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_excercice01_05 = new byte[0];

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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

			synchronized (commonByteArrayLock_MISSION_TALEND_excercice01_05) {

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

	public void tHashInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tHashInput_1_SUBPROCESS_STATE", 0);

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

				row3Struct row3 = new row3Struct();
				row4Struct row4 = new row4Struct();
				out1Struct out1 = new out1Struct();
				out1Struct row5 = out1;
				row6Struct row6 = new row6Struct();
				row7Struct row7 = new row7Struct();
				affectStruct affect = new affectStruct();

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

						if (this.HireDate == null && other.HireDate != null) {
							return 1;

						} else if (this.HireDate != null && other.HireDate == null) {
							return -1;

						} else if (this.HireDate != null && other.HireDate != null) {
							if (!this.HireDate.equals(other.HireDate)) {
								return other.HireDate.compareTo(this.HireDate);
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
				 * [tHashInput_1 begin ] start
				 */

				ok_Hash.put("tHashInput_1", false);
				start_Hash.put("tHashInput_1", System.currentTimeMillis());

				currentComponent = "tHashInput_1";

				int tos_count_tHashInput_1 = 0;

				int nb_line_tHashInput_1 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row1Struct> tHashFile_tHashInput_1 = mf_tHashInput_1
						.getAdvancedMemoryHashFile("tHashFile_excercice01_05_" + pid + "_tHashOutput_1");
				if (tHashFile_tHashInput_1 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<row1Struct> iterator_tHashInput_1 = tHashFile_tHashInput_1.iterator();
				while (iterator_tHashInput_1.hasNext()) {
					row1Struct next_tHashInput_1 = iterator_tHashInput_1.next();

					row3.EmployeeID = next_tHashInput_1.EmployeeID;
					row3.ManagerID = next_tHashInput_1.ManagerID;
					row3.DepartmentID = next_tHashInput_1.DepartmentID;
					row3.HireDate = next_tHashInput_1.HireDate;
					row3.nbEmployees = next_tHashInput_1.nbEmployees;

					/**
					 * [tHashInput_1 begin ] stop
					 */

					/**
					 * [tHashInput_1 main ] start
					 */

					currentComponent = "tHashInput_1";

					tos_count_tHashInput_1++;

					/**
					 * [tHashInput_1 main ] stop
					 */

					/**
					 * [tHashInput_1 process_data_begin ] start
					 */

					currentComponent = "tHashInput_1";

					/**
					 * [tHashInput_1 process_data_begin ] stop
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
					arrayRowtSortRow_1_SortOut.ManagerID = row3.ManagerID;
					arrayRowtSortRow_1_SortOut.DepartmentID = row3.DepartmentID;
					arrayRowtSortRow_1_SortOut.HireDate = row3.HireDate;
					arrayRowtSortRow_1_SortOut.nbEmployees = row3.nbEmployees;
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
					 * [tHashInput_1 process_data_end ] start
					 */

					currentComponent = "tHashInput_1";

					/**
					 * [tHashInput_1 process_data_end ] stop
					 */

					/**
					 * [tHashInput_1 end ] start
					 */

					currentComponent = "tHashInput_1";

					nb_line_tHashInput_1++;
				}

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_excercice01_05_" + pid + "_tHashOutput_1");

				globalMap.put("tHashInput_1_NB_LINE", nb_line_tHashInput_1);

				ok_Hash.put("tHashInput_1", true);
				end_Hash.put("tHashInput_1", System.currentTimeMillis());

				/**
				 * [tHashInput_1 end ] stop
				 */

				/**
				 * [tSortRow_1_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				row3Struct[] array_tSortRow_1_SortOut = list_tSortRow_1_SortOut.toArray(new Comparablerow3Struct[0]);

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
				 * [tFlowToIterate_1 begin ] start
				 */

				int NB_ITERATE_tHashInput_2 = 0; // for statistics

				ok_Hash.put("tFlowToIterate_1", false);
				start_Hash.put("tFlowToIterate_1", System.currentTimeMillis());

				currentComponent = "tFlowToIterate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tFlowToIterate_1 = 0;

				int nb_line_tFlowToIterate_1 = 0;
				int counter_tFlowToIterate_1 = 0;

				/**
				 * [tFlowToIterate_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					int var1;
					int var2;
					int var3;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
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
					row4.ManagerID = current_tSortRow_1_SortIn.ManagerID;
					row4.DepartmentID = current_tSortRow_1_SortIn.DepartmentID;
					row4.HireDate = current_tSortRow_1_SortIn.HireDate;
					row4.nbEmployees = current_tSortRow_1_SortIn.nbEmployees;
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
					 * [tMap_1 main ] start
					 */

					currentComponent = "tMap_1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row4"

						);
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

					// ###############################
					// # Input tables (lookups)
					boolean rejectedInnerJoin_tMap_1 = false;
					boolean mainRowRejected_tMap_1 = false;

					// ###############################
					{ // start of Var scope

						// ###############################
						// # Vars tables

						Var__tMap_1__Struct Var = Var__tMap_1;
						Var.var1 = Numeric.sequence("s1", 1, 1);
						Var.var2 = (Var.var1 - 1) * ((Integer) globalMap.get("x")) + 1;
						Var.var3 = ((Integer) globalMap.get("tFileInputDelimited_2_NB_LINE")) == (Var.var1)
								? ((Integer) globalMap.get("tFileInputDelimited_1_NB_LINE"))
								: ((Integer) globalMap.get("x")) * Var.var1;// ###############################
						// ###############################
						// # Output tables

						out1 = null;

// # Output table : 'out1'
						out1_tmp.EmployeeID = row4.EmployeeID;
						out1_tmp.ManagerID = row4.ManagerID;
						out1_tmp.DepartmentID = row4.DepartmentID;
						out1_tmp.HireDate = row4.HireDate;
						out1_tmp.nbEmployees = row4.nbEmployees;
						out1_tmp.sample = Var.var2 + ".." + Var.var3;
						out1 = out1_tmp;
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
						 * [tLogRow_1 main ] start
						 */

						currentComponent = "tLogRow_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "out1"

							);
						}

///////////////////////		

						strBuffer_tLogRow_1 = new StringBuilder();

						if (out1.EmployeeID != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(out1.EmployeeID));

						} //

						strBuffer_tLogRow_1.append("|");

						if (out1.ManagerID != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(out1.ManagerID));

						} //

						strBuffer_tLogRow_1.append("|");

						if (out1.DepartmentID != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(out1.DepartmentID));

						} //

						strBuffer_tLogRow_1.append("|");

						if (out1.HireDate != null) { //

							strBuffer_tLogRow_1.append(FormatterUtils.format_Date(out1.HireDate, "dd-MM-yyyy"));

						} //

						strBuffer_tLogRow_1.append("|");

						if (out1.nbEmployees != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(out1.nbEmployees));

						} //

						strBuffer_tLogRow_1.append("|");

						if (out1.sample != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(out1.sample));

						} //

						if (globalMap.get("tLogRow_CONSOLE") != null) {
							consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
						} else {
							consoleOut_tLogRow_1 = new java.io.PrintStream(
									new java.io.BufferedOutputStream(System.out));
							globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
						}
						consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
						consoleOut_tLogRow_1.flush();
						nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

						row5 = out1;

						tos_count_tLogRow_1++;

						/**
						 * [tLogRow_1 main ] stop
						 */

						/**
						 * [tLogRow_1 process_data_begin ] start
						 */

						currentComponent = "tLogRow_1";

						/**
						 * [tLogRow_1 process_data_begin ] stop
						 */

						/**
						 * [tFlowToIterate_1 main ] start
						 */

						currentComponent = "tFlowToIterate_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row5"

							);
						}

						globalMap.put("row5.EmployeeID", row5.EmployeeID);

						globalMap.put("row5.ManagerID", row5.ManagerID);

						globalMap.put("row5.DepartmentID", row5.DepartmentID);

						globalMap.put("row5.HireDate", row5.HireDate);

						globalMap.put("row5.nbEmployees", row5.nbEmployees);

						globalMap.put("row5.sample", row5.sample);

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
						NB_ITERATE_tHashInput_2++;

						if (execStat) {
							runStat.updateStatOnConnection("row6", 3, 0);
						}

						if (execStat) {
							runStat.updateStatOnConnection("affect", 3, 0);
						}

						if (execStat) {
							runStat.updateStatOnConnection("row7", 3, 0);
						}

						if (execStat) {
							runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tHashInput_2);
							// Thread.sleep(1000);
						}

						/**
						 * [tFileOutputDelimited_1 begin ] start
						 */

						ok_Hash.put("tFileOutputDelimited_1", false);
						start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

						currentComponent = "tFileOutputDelimited_1";

						if (execStat) {
							runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "affect");
						}

						int tos_count_tFileOutputDelimited_1 = 0;

						String fileName_tFileOutputDelimited_1 = "";
						fileName_tFileOutputDelimited_1 = (new java.io.File(
								"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/affectation.csv")).getAbsolutePath()
										.replace("\\", "/");
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
								new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
						if (filetFileOutputDelimited_1.length() == 0) {
							outtFileOutputDelimited_1.write("nom");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("prenom");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("datedenaissance");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("salairedemande");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("numTel");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("JobCode");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("MinSalary");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("MaxSalary");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("ManagerID");
							outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.write("DepartementID");
							outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
							outtFileOutputDelimited_1.flush();
						}

						resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
						resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

						/**
						 * [tFileOutputDelimited_1 begin ] stop
						 */

						/**
						 * [tMap_2 begin ] start
						 */

						ok_Hash.put("tMap_2", false);
						start_Hash.put("tMap_2", System.currentTimeMillis());

						currentComponent = "tMap_2";

						if (execStat) {
							runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
						}

						int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
						class Var__tMap_2__Struct {
						}
						Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
						affectStruct affect_tmp = new affectStruct();
// ###############################

						/**
						 * [tMap_2 begin ] stop
						 */

						/**
						 * [tSampleRow_1 begin ] start
						 */

						ok_Hash.put("tSampleRow_1", false);
						start_Hash.put("tSampleRow_1", System.currentTimeMillis());

						currentComponent = "tSampleRow_1";

						if (execStat) {
							runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
						}

						int tos_count_tSampleRow_1 = 0;

						String[] rangetSampleRow_1 = ((String) globalMap.get("row5.sample")).split(",");
						java.util.Set rangeSettSampleRow_1 = new java.util.HashSet();

						Integer nb_line_tSampleRow_1 = 0;

						for (int i = 0; i < rangetSampleRow_1.length; i++) {

							if (rangetSampleRow_1[i].matches("\\d+")) {

								rangeSettSampleRow_1.add(Integer.valueOf(rangetSampleRow_1[i]));

							} else if (rangetSampleRow_1[i].matches("\\d+\\.\\.\\d+")) {

								String[] edgetSampleRow_1 = rangetSampleRow_1[i].split("\\.\\.");

								for (int j = Integer.valueOf(edgetSampleRow_1[0]).intValue(); j < Integer
										.valueOf(edgetSampleRow_1[1]).intValue() + 1; j++) {
									rangeSettSampleRow_1.add(Integer.valueOf(j));
								}
							} else {

							}

						}

						/**
						 * [tSampleRow_1 begin ] stop
						 */

						/**
						 * [tHashInput_2 begin ] start
						 */

						ok_Hash.put("tHashInput_2", false);
						start_Hash.put("tHashInput_2", System.currentTimeMillis());

						currentComponent = "tHashInput_2";

						int tos_count_tHashInput_2 = 0;

						int nb_line_tHashInput_2 = 0;

						org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_2 = org.talend.designer.components.hashfile.common.MapHashFile
								.getMapHashFile();
						org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row2Struct> tHashFile_tHashInput_2 = mf_tHashInput_2
								.getAdvancedMemoryHashFile("tHashFile_excercice01_05_" + pid + "_tHashOutput_2");
						if (tHashFile_tHashInput_2 == null) {
							throw new RuntimeException(
									"The hash is not initialized : The hash must exist before you read from it");
						}
						java.util.Iterator<row2Struct> iterator_tHashInput_2 = tHashFile_tHashInput_2.iterator();
						while (iterator_tHashInput_2.hasNext()) {
							row2Struct next_tHashInput_2 = iterator_tHashInput_2.next();

							row6.nom = next_tHashInput_2.nom;
							row6.prenom = next_tHashInput_2.prenom;
							row6.datedenaissance = next_tHashInput_2.datedenaissance;
							row6.salairedemande = next_tHashInput_2.salairedemande;
							row6.numTel = next_tHashInput_2.numTel;
							row6.JobCode = next_tHashInput_2.JobCode;
							row6.MinSalary = next_tHashInput_2.MinSalary;
							row6.MaxSalary = next_tHashInput_2.MaxSalary;

							/**
							 * [tHashInput_2 begin ] stop
							 */

							/**
							 * [tHashInput_2 main ] start
							 */

							currentComponent = "tHashInput_2";

							tos_count_tHashInput_2++;

							/**
							 * [tHashInput_2 main ] stop
							 */

							/**
							 * [tHashInput_2 process_data_begin ] start
							 */

							currentComponent = "tHashInput_2";

							/**
							 * [tHashInput_2 process_data_begin ] stop
							 */

							/**
							 * [tSampleRow_1 main ] start
							 */

							currentComponent = "tSampleRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row6"

								);
							}

							nb_line_tSampleRow_1++;

							if (!rangeSettSampleRow_1.contains(nb_line_tSampleRow_1)) {
								row7 = null;
							} else {
								row7 = new row7Struct();

								row7.nom = row6.nom;

								row7.prenom = row6.prenom;

								row7.datedenaissance = row6.datedenaissance;

								row7.salairedemande = row6.salairedemande;

								row7.numTel = row6.numTel;

								row7.JobCode = row6.JobCode;

								row7.MinSalary = row6.MinSalary;

								row7.MaxSalary = row6.MaxSalary;

							}

							tos_count_tSampleRow_1++;

							/**
							 * [tSampleRow_1 main ] stop
							 */

							/**
							 * [tSampleRow_1 process_data_begin ] start
							 */

							currentComponent = "tSampleRow_1";

							/**
							 * [tSampleRow_1 process_data_begin ] stop
							 */
// Start of branch "row7"
							if (row7 != null) {

								/**
								 * [tMap_2 main ] start
								 */

								currentComponent = "tMap_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row7"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_2 = false;
								boolean mainRowRejected_tMap_2 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
									// ###############################
									// # Output tables

									affect = null;

// # Output table : 'affect'
									affect_tmp.nom = row7.nom;
									affect_tmp.prenom = row7.prenom;
									affect_tmp.datedenaissance = row7.datedenaissance;
									affect_tmp.salairedemande = row7.salairedemande;
									affect_tmp.numTel = row7.numTel;
									affect_tmp.JobCode = row7.JobCode;
									affect_tmp.MinSalary = row7.MinSalary;
									affect_tmp.MaxSalary = row7.MaxSalary;
									affect_tmp.ManagerID = ((Integer) globalMap.get("row5.EmployeeID"));
									affect_tmp.DepartementID = ((Integer) globalMap.get("row5.DepartmentID"));
									affect = affect_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_2 = false;

								tos_count_tMap_2++;

								/**
								 * [tMap_2 main ] stop
								 */

								/**
								 * [tMap_2 process_data_begin ] start
								 */

								currentComponent = "tMap_2";

								/**
								 * [tMap_2 process_data_begin ] stop
								 */
// Start of branch "affect"
								if (affect != null) {

									/**
									 * [tFileOutputDelimited_1 main ] start
									 */

									currentComponent = "tFileOutputDelimited_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "affect"

										);
									}

									StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
									if (affect.nom != null) {
										sb_tFileOutputDelimited_1.append(affect.nom);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.prenom != null) {
										sb_tFileOutputDelimited_1.append(affect.prenom);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.datedenaissance != null) {
										sb_tFileOutputDelimited_1.append(
												FormatterUtils.format_Date(affect.datedenaissance, "dd-MM-yyyy"));
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.salairedemande != null) {
										sb_tFileOutputDelimited_1.append(affect.salairedemande);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.numTel != null) {
										sb_tFileOutputDelimited_1.append(affect.numTel);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.JobCode != null) {
										sb_tFileOutputDelimited_1.append(affect.JobCode);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.MinSalary != null) {
										sb_tFileOutputDelimited_1.append(affect.MinSalary);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.MaxSalary != null) {
										sb_tFileOutputDelimited_1.append(affect.MaxSalary);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.ManagerID != null) {
										sb_tFileOutputDelimited_1.append(affect.ManagerID);
									}
									sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
									if (affect.DepartementID != null) {
										sb_tFileOutputDelimited_1.append(affect.DepartementID);
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

								} // End of branch "affect"

								/**
								 * [tMap_2 process_data_end ] start
								 */

								currentComponent = "tMap_2";

								/**
								 * [tMap_2 process_data_end ] stop
								 */

							} // End of branch "row7"

							/**
							 * [tSampleRow_1 process_data_end ] start
							 */

							currentComponent = "tSampleRow_1";

							/**
							 * [tSampleRow_1 process_data_end ] stop
							 */

							/**
							 * [tHashInput_2 process_data_end ] start
							 */

							currentComponent = "tHashInput_2";

							/**
							 * [tHashInput_2 process_data_end ] stop
							 */

							/**
							 * [tHashInput_2 end ] start
							 */

							currentComponent = "tHashInput_2";

							nb_line_tHashInput_2++;
						}

						org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
								.remove("tHashFile_excercice01_05_" + pid + "_tHashOutput_2");

						globalMap.put("tHashInput_2_NB_LINE", nb_line_tHashInput_2);

						ok_Hash.put("tHashInput_2", true);
						end_Hash.put("tHashInput_2", System.currentTimeMillis());

						/**
						 * [tHashInput_2 end ] stop
						 */

						/**
						 * [tSampleRow_1 end ] start
						 */

						currentComponent = "tSampleRow_1";

						if (execStat) {
							runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
						}

						ok_Hash.put("tSampleRow_1", true);
						end_Hash.put("tSampleRow_1", System.currentTimeMillis());

						/**
						 * [tSampleRow_1 end ] stop
						 */

						/**
						 * [tMap_2 end ] start
						 */

						currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
// ###############################      

						if (execStat) {
							runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
						}

						ok_Hash.put("tMap_2", true);
						end_Hash.put("tMap_2", System.currentTimeMillis());

						/**
						 * [tMap_2 end ] stop
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
							runStat.updateStat(resourceMap, iterateId, 2, 0, "affect");
						}

						ok_Hash.put("tFileOutputDelimited_1", true);
						end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

						/**
						 * [tFileOutputDelimited_1 end ] stop
						 */

						if (execStat) {
							runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tHashInput_2);
						}

						/**
						 * [tFlowToIterate_1 process_data_end ] start
						 */

						currentComponent = "tFlowToIterate_1";

						/**
						 * [tFlowToIterate_1 process_data_end ] stop
						 */

						/**
						 * [tLogRow_1 process_data_end ] start
						 */

						currentComponent = "tLogRow_1";

						/**
						 * [tLogRow_1 process_data_end ] stop
						 */

					} // End of branch "out1"

					/**
					 * [tMap_1 process_data_end ] start
					 */

					currentComponent = "tMap_1";

					/**
					 * [tMap_1 process_data_end ] stop
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
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tFlowToIterate_1 end ] start
				 */

				currentComponent = "tFlowToIterate_1";

				globalMap.put("tFlowToIterate_1_NB_LINE", nb_line_tFlowToIterate_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
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

			// free memory for "tSortRow_1_SortIn"
			globalMap.remove("tSortRow_1");

			try {

				/**
				 * [tHashInput_1 finally ] start
				 */

				currentComponent = "tHashInput_1";

				/**
				 * [tHashInput_1 finally ] stop
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
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tFlowToIterate_1 finally ] start
				 */

				currentComponent = "tFlowToIterate_1";

				/**
				 * [tFlowToIterate_1 finally ] stop
				 */

				/**
				 * [tHashInput_2 finally ] start
				 */

				currentComponent = "tHashInput_2";

				/**
				 * [tHashInput_2 finally ] stop
				 */

				/**
				 * [tSampleRow_1 finally ] start
				 */

				currentComponent = "tSampleRow_1";

				/**
				 * [tSampleRow_1 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
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

		globalMap.put("tHashInput_1_SUBPROCESS_STATE", 1);
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
		final excercice01_05 excercice01_05Class = new excercice01_05();

		int exitCode = excercice01_05Class.runJobInTOS(args);

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
			java.io.InputStream inContext = excercice01_05.class.getClassLoader()
					.getResourceAsStream("mission_talend/excercice01_05_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = excercice01_05.class.getClassLoader()
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
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : excercice01_05");
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
 * 189913 characters generated by Talend Open Studio for Data Integration on the
 * 9 février 2023 à 21:45:14 GMT+01:00
 ************************************************************************************************/