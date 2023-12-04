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

package mission_talend.exercice09_01_0_2;

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
 * Job: exercice09_01 Purpose: Génération des fichiers CSV<br>
 * Description: Générer un fichier CSV contenant les données de candidats. <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status TEST
 */
public class exercice09_01 implements TalendJob {

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

	private final String jobVersion = "0.2";
	private final String jobName = "exercice09_01";
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

	LogCatcherUtils tLogCatcher_1 = new LogCatcherUtils();
	LogCatcherUtils talendLogs_LOGS = new LogCatcherUtils();
	StatCatcherUtils talendStats_STATS = new StatCatcherUtils("_OvQXMKlLEe27muNCIAdxOw", "0.2");

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
					exercice09_01.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(exercice09_01.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						tLogCatcher_1.addMessage("Java Exception", currentComponent, 6,
								e.getClass().getName() + ":" + e.getMessage(), 1);
						talendLogs_LOGS.addMessage("Java Exception", currentComponent, 6,
								e.getClass().getName() + ":" + e.getMessage(), 1);
						try {
							tLogCatcher_1Process(globalMap);
						} finally {
							talendLogs_LOGSProcess(globalMap);
						}
					}
				} catch (TalendException e) {
					// do nothing

				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void preStaLogCon_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		preStaLogCon_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogCatcher_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tWarn_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRowGenerator_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		try {

			if (this.execStat) {
				runStat.updateStatOnConnection("OnComponentError1", 0, "error");
			}

			errorCode = null;
			tDie_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDie_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDie_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void vFlowMeter_row1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void connectionStatsLogs_Commit_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		connectionStatsLogs_Commit_onSubJobError(exception, errorComponent, globalMap);
	}

	public void connectionStatsLogs_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		connectionStatsLogs_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendStats_STATS_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		talendStats_DB_error(exception, errorComponent, globalMap);

	}

	public void talendStats_DB_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendStats_STATS_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendLogs_LOGS_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		talendLogs_DB_error(exception, errorComponent, globalMap);

	}

	public void talendLogs_DB_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendLogs_LOGS_onSubJobError(exception, errorComponent, globalMap);
	}

	public void preStaLogCon_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tLogCatcher_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tWarn_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tRowGenerator_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDie_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void connectionStatsLogs_Commit_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void connectionStatsLogs_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendStats_STATS_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendLogs_LOGS_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void preStaLogConProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("preStaLogCon_SUBPROCESS_STATE", 0);

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
				 * [preStaLogCon begin ] start
				 */

				ok_Hash.put("preStaLogCon", false);
				start_Hash.put("preStaLogCon", System.currentTimeMillis());

				currentComponent = "preStaLogCon";

				int tos_count_preStaLogCon = 0;

				/**
				 * [preStaLogCon begin ] stop
				 */

				/**
				 * [preStaLogCon main ] start
				 */

				currentComponent = "preStaLogCon";

				tos_count_preStaLogCon++;

				/**
				 * [preStaLogCon main ] stop
				 */

				/**
				 * [preStaLogCon process_data_begin ] start
				 */

				currentComponent = "preStaLogCon";

				/**
				 * [preStaLogCon process_data_begin ] stop
				 */

				/**
				 * [preStaLogCon process_data_end ] start
				 */

				currentComponent = "preStaLogCon";

				/**
				 * [preStaLogCon process_data_end ] stop
				 */

				/**
				 * [preStaLogCon end ] start
				 */

				currentComponent = "preStaLogCon";

				ok_Hash.put("preStaLogCon", true);
				end_Hash.put("preStaLogCon", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("after_preStaLogCon_connectionStatsLogs", 0, "ok");
				}
				connectionStatsLogsProcess(globalMap);

				/**
				 * [preStaLogCon end ] stop
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
				 * [preStaLogCon finally ] start
				 */

				currentComponent = "preStaLogCon";

				/**
				 * [preStaLogCon finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("preStaLogCon_SUBPROCESS_STATE", 1);
	}

	public static class outStruct implements routines.system.IPersistableRow<outStruct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice09_01 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice09_01 = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public Integer priority;

		public Integer getPriority() {
			return this.priority;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String user;

		public String getUser() {
			return this.user;
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

					this.user = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

					this.user = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.user, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.user, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",context=" + context);
			sb.append(",priority=" + String.valueOf(priority));
			sb.append(",type=" + type);
			sb.append(",origin=" + origin);
			sb.append(",message=" + message);
			sb.append(",code=" + String.valueOf(code));
			sb.append(",user=" + user);
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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice09_01 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice09_01 = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public Integer priority;

		public Integer getPriority() {
			return this.priority;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Integer code;

		public Integer getCode() {
			return this.code;
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",context=" + context);
			sb.append(",priority=" + String.valueOf(priority));
			sb.append(",type=" + type);
			sb.append(",origin=" + origin);
			sb.append(",message=" + message);
			sb.append(",code=" + String.valueOf(code));
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

	public void tLogCatcher_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tLogCatcher_1_SUBPROCESS_STATE", 0);

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
				outStruct out = new outStruct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out");
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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
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
				 * [tLogCatcher_1 begin ] start
				 */

				ok_Hash.put("tLogCatcher_1", false);
				start_Hash.put("tLogCatcher_1", System.currentTimeMillis());

				currentComponent = "tLogCatcher_1";

				int tos_count_tLogCatcher_1 = 0;

				try {
					for (LogCatcherUtils.LogCatcherMessage lcm : tLogCatcher_1.getMessages()) {
						row2.type = lcm.getType();
						row2.origin = (lcm.getOrigin() == null || lcm.getOrigin().length() < 1 ? null
								: lcm.getOrigin());
						row2.priority = lcm.getPriority();
						row2.message = lcm.getMessage();
						row2.code = lcm.getCode();

						row2.moment = java.util.Calendar.getInstance().getTime();

						row2.pid = pid;
						row2.root_pid = rootPid;
						row2.father_pid = fatherPid;

						row2.project = projectName;
						row2.job = jobName;
						row2.context = contextStr;

						/**
						 * [tLogCatcher_1 begin ] stop
						 */

						/**
						 * [tLogCatcher_1 main ] start
						 */

						currentComponent = "tLogCatcher_1";

						tos_count_tLogCatcher_1++;

						/**
						 * [tLogCatcher_1 main ] stop
						 */

						/**
						 * [tLogCatcher_1 process_data_begin ] start
						 */

						currentComponent = "tLogCatcher_1";

						/**
						 * [tLogCatcher_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

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

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							out = null;

// # Output table : 'out'
							out_tmp.moment = row2.moment;
							out_tmp.pid = row2.pid;
							out_tmp.root_pid = row2.root_pid;
							out_tmp.father_pid = row2.father_pid;
							out_tmp.project = row2.project;
							out_tmp.job = row2.job;
							out_tmp.context = row2.context;
							out_tmp.priority = row2.priority;
							out_tmp.type = row2.type;
							out_tmp.origin = row2.origin;
							out_tmp.message = row2.message;
							out_tmp.code = row2.code;
							out_tmp.user = System.getProperty("user.name");
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
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "out"

								);
							}

///////////////////////		

							strBuffer_tLogRow_1 = new StringBuilder();

							if (out.moment != null) { //

								strBuffer_tLogRow_1
										.append(FormatterUtils.format_Date(out.moment, "yyyy-MM-dd HH:mm:ss"));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.pid != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.pid));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.root_pid != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.root_pid));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.father_pid != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.father_pid));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.project != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.project));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.job != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.job));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.context != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.context));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.priority != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.priority));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.type != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.type));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.origin != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.origin));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.message != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.message));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.code != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.code));

							} //

							strBuffer_tLogRow_1.append("|");

							if (out.user != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.user));

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
							 * [tLogRow_1 process_data_end ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_end ] stop
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
						 * [tLogCatcher_1 process_data_end ] start
						 */

						currentComponent = "tLogCatcher_1";

						/**
						 * [tLogCatcher_1 process_data_end ] stop
						 */

						/**
						 * [tLogCatcher_1 end ] start
						 */

						currentComponent = "tLogCatcher_1";

					}
				} catch (Exception e_tLogCatcher_1) {
					globalMap.put("tLogCatcher_1_ERROR_MESSAGE", e_tLogCatcher_1.getMessage());
					logIgnoredError(String.format(
							"tLogCatcher_1 - tLogCatcher failed to process log message(s) due to internal error: %s",
							e_tLogCatcher_1), e_tLogCatcher_1);
				}

				ok_Hash.put("tLogCatcher_1", true);
				end_Hash.put("tLogCatcher_1", System.currentTimeMillis());

				/**
				 * [tLogCatcher_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
				 * [tLogCatcher_1 finally ] start
				 */

				currentComponent = "tLogCatcher_1";

				/**
				 * [tLogCatcher_1 finally ] stop
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

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tLogCatcher_1_SUBPROCESS_STATE", 1);
	}

	public void tWarn_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tWarn_1_SUBPROCESS_STATE", 0);

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
				 * [tWarn_1 begin ] start
				 */

				ok_Hash.put("tWarn_1", false);
				start_Hash.put("tWarn_1", System.currentTimeMillis());

				currentComponent = "tWarn_1";

				int tos_count_tWarn_1 = 0;

				/**
				 * [tWarn_1 begin ] stop
				 */

				/**
				 * [tWarn_1 main ] start
				 */

				currentComponent = "tWarn_1";

				try {

					resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_1", "", Thread.currentThread().getId() + "", "WARN",
							"", "Lancement du JOB", "", "");
					tLogCatcher_1.addMessage("tWarn", "tWarn_1", 4, "Lancement du JOB", 42);
					tLogCatcher_1Process(globalMap);
					talendLogs_LOGS.addMessage("tWarn", "tWarn_1", 4, "Lancement du JOB", 42);
					talendLogs_LOGSProcess(globalMap);
					globalMap.put("tWarn_1_WARN_MESSAGES", "Lancement du JOB");
					globalMap.put("tWarn_1_WARN_PRIORITY", 4);
					globalMap.put("tWarn_1_WARN_CODE", 42);

				} catch (Exception e_tWarn_1) {
					globalMap.put("tWarn_1_ERROR_MESSAGE", e_tWarn_1.getMessage());
					logIgnoredError(
							String.format("tWarn_1 - tWarn failed to log message due to internal error: %s", e_tWarn_1),
							e_tWarn_1);
				}

				tos_count_tWarn_1++;

				/**
				 * [tWarn_1 main ] stop
				 */

				/**
				 * [tWarn_1 process_data_begin ] start
				 */

				currentComponent = "tWarn_1";

				/**
				 * [tWarn_1 process_data_begin ] stop
				 */

				/**
				 * [tWarn_1 process_data_end ] start
				 */

				currentComponent = "tWarn_1";

				/**
				 * [tWarn_1 process_data_end ] stop
				 */

				/**
				 * [tWarn_1 end ] start
				 */

				currentComponent = "tWarn_1";

				ok_Hash.put("tWarn_1", true);
				end_Hash.put("tWarn_1", System.currentTimeMillis());

				/**
				 * [tWarn_1 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tWarn_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tRowGenerator_1Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tWarn_1 finally ] start
				 */

				currentComponent = "tWarn_1";

				/**
				 * [tWarn_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tWarn_1_SUBPROCESS_STATE", 1);
	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice09_01 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice09_01 = new byte[0];

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.nom = readString(dis);

					this.prenom = readString(dis);

					this.datedenaissance = readDate(dis);

					this.salairedemande = readInteger(dis);

					this.numTel = readInteger(dis);

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

	public void tRowGenerator_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tRowGenerator_1_SUBPROCESS_STATE", 0);

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
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "meterRowrow1");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/candidats.csv")).getAbsolutePath()
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
				if (filetFileOutputDelimited_1.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_1.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
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
				 * [vFlowMeter_row1 begin ] start
				 */

				ok_Hash.put("vFlowMeter_row1", false);
				start_Hash.put("vFlowMeter_row1", System.currentTimeMillis());

				currentComponent = "vFlowMeter_row1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_vFlowMeter_row1 = 0;

				int count_vFlowMeter_row1 = 0;

				/**
				 * [vFlowMeter_row1 begin ] stop
				 */

				/**
				 * [tRowGenerator_1 begin ] start
				 */

				ok_Hash.put("tRowGenerator_1", false);
				start_Hash.put("tRowGenerator_1", System.currentTimeMillis());

				currentComponent = "tRowGenerator_1";

				int tos_count_tRowGenerator_1 = 0;

				int nb_line_tRowGenerator_1 = 0;
				int nb_max_row_tRowGenerator_1 = 10000;

				class tRowGenerator_1Randomizer {
					public String getRandomnom() {

						return TalendDataGenerator.getLastName();

					}

					public String getRandomprenom() {

						return TalendDataGenerator.getFirstName();

					}

					public java.util.Date getRandomdatedenaissance() {

						return TalendDate.getRandomDate("1970-01-01", "1990-12-31");

					}

					public Integer getRandomsalairedemande() {

						return Numeric.random(2000, 20000);

					}

					public Integer getRandomnumTel() {

						return Numeric.random(10000000, 99999999);

					}
				}
				tRowGenerator_1Randomizer randtRowGenerator_1 = new tRowGenerator_1Randomizer();

				for (int itRowGenerator_1 = 0; itRowGenerator_1 < nb_max_row_tRowGenerator_1; itRowGenerator_1++) {
					row1.nom = randtRowGenerator_1.getRandomnom();
					row1.prenom = randtRowGenerator_1.getRandomprenom();
					row1.datedenaissance = randtRowGenerator_1.getRandomdatedenaissance();
					row1.salairedemande = randtRowGenerator_1.getRandomsalairedemande();
					row1.numTel = randtRowGenerator_1.getRandomnumTel();
					nb_line_tRowGenerator_1++;

					/**
					 * [tRowGenerator_1 begin ] stop
					 */

					/**
					 * [tRowGenerator_1 main ] start
					 */

					currentComponent = "tRowGenerator_1";

					tos_count_tRowGenerator_1++;

					/**
					 * [tRowGenerator_1 main ] stop
					 */

					/**
					 * [tRowGenerator_1 process_data_begin ] start
					 */

					currentComponent = "tRowGenerator_1";

					/**
					 * [tRowGenerator_1 process_data_begin ] stop
					 */

					/**
					 * [vFlowMeter_row1 main ] start
					 */

					currentComponent = "vFlowMeter_row1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row1"

						);
					}

					count_vFlowMeter_row1++;

					tos_count_vFlowMeter_row1++;

					/**
					 * [vFlowMeter_row1 main ] stop
					 */

					/**
					 * [vFlowMeter_row1 process_data_begin ] start
					 */

					currentComponent = "vFlowMeter_row1";

					/**
					 * [vFlowMeter_row1 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_1 main ] start
					 */

					currentComponent = "tFileOutputDelimited_1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "meterRowrow1"

						);
					}

					StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
					if (row1.nom != null) {
						sb_tFileOutputDelimited_1.append(row1.nom);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row1.prenom != null) {
						sb_tFileOutputDelimited_1.append(row1.prenom);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row1.datedenaissance != null) {
						sb_tFileOutputDelimited_1
								.append(FormatterUtils.format_Date(row1.datedenaissance, "dd-MM-yyyy"));
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row1.salairedemande != null) {
						sb_tFileOutputDelimited_1.append(row1.salairedemande);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row1.numTel != null) {
						sb_tFileOutputDelimited_1.append(row1.numTel);
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

					/**
					 * [vFlowMeter_row1 process_data_end ] start
					 */

					currentComponent = "vFlowMeter_row1";

					/**
					 * [vFlowMeter_row1 process_data_end ] stop
					 */

					/**
					 * [tRowGenerator_1 process_data_end ] start
					 */

					currentComponent = "tRowGenerator_1";

					/**
					 * [tRowGenerator_1 process_data_end ] stop
					 */

					/**
					 * [tRowGenerator_1 end ] start
					 */

					currentComponent = "tRowGenerator_1";

				}
				globalMap.put("tRowGenerator_1_NB_LINE", nb_line_tRowGenerator_1);

				ok_Hash.put("tRowGenerator_1", true);
				end_Hash.put("tRowGenerator_1", System.currentTimeMillis());

				/**
				 * [tRowGenerator_1 end ] stop
				 */

				/**
				 * [vFlowMeter_row1 end ] start
				 */

				currentComponent = "vFlowMeter_row1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("vFlowMeter_row1", true);
				end_Hash.put("vFlowMeter_row1", System.currentTimeMillis());

				/**
				 * [vFlowMeter_row1 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "meterRowrow1");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
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
				 * [tRowGenerator_1 finally ] start
				 */

				currentComponent = "tRowGenerator_1";

				/**
				 * [tRowGenerator_1 finally ] stop
				 */

				/**
				 * [vFlowMeter_row1 finally ] start
				 */

				currentComponent = "vFlowMeter_row1";

				/**
				 * [vFlowMeter_row1 finally ] stop
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

		globalMap.put("tRowGenerator_1_SUBPROCESS_STATE", 1);
	}

	public void tDie_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDie_1_SUBPROCESS_STATE", 0);

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
				 * [tDie_1 begin ] start
				 */

				ok_Hash.put("tDie_1", false);
				start_Hash.put("tDie_1", System.currentTimeMillis());

				currentComponent = "tDie_1";

				int tos_count_tDie_1 = 0;

				/**
				 * [tDie_1 begin ] stop
				 */

				/**
				 * [tDie_1 main ] start
				 */

				currentComponent = "tDie_1";

				try {
					tLogCatcher_1.addMessage("tDie", "tDie_1", 5,
							((String) globalMap.get("tFileOutputDelimited_1_ERROR_MESSAGE")), 4);
					tLogCatcher_1Process(globalMap);

					talendLogs_LOGS.addMessage("tDie", "tDie_1", 5,
							((String) globalMap.get("tFileOutputDelimited_1_ERROR_MESSAGE")), 4);
					talendLogs_LOGSProcess(globalMap);

					globalMap.put("tDie_1_DIE_PRIORITY", 5);
					System.err.println(((String) globalMap.get("tFileOutputDelimited_1_ERROR_MESSAGE")));

					globalMap.put("tDie_1_DIE_MESSAGE",
							((String) globalMap.get("tFileOutputDelimited_1_ERROR_MESSAGE")));
					globalMap.put("tDie_1_DIE_MESSAGES",
							((String) globalMap.get("tFileOutputDelimited_1_ERROR_MESSAGE")));

				} catch (Exception | Error e_tDie_1) {
					globalMap.put("tDie_1_ERROR_MESSAGE", e_tDie_1.getMessage());
					logIgnoredError(
							String.format("tDie_1 - tDie failed to log message due to internal error: %s", e_tDie_1),
							e_tDie_1);
				}

				currentComponent = "tDie_1";
				status = "failure";
				errorCode = new Integer(4);
				globalMap.put("tDie_1_DIE_CODE", errorCode);

				if (true) {
					throw new TDieException();
				}

				tos_count_tDie_1++;

				/**
				 * [tDie_1 main ] stop
				 */

				/**
				 * [tDie_1 process_data_begin ] start
				 */

				currentComponent = "tDie_1";

				/**
				 * [tDie_1 process_data_begin ] stop
				 */

				/**
				 * [tDie_1 process_data_end ] start
				 */

				currentComponent = "tDie_1";

				/**
				 * [tDie_1 process_data_end ] stop
				 */

				/**
				 * [tDie_1 end ] start
				 */

				currentComponent = "tDie_1";

				ok_Hash.put("tDie_1", true);
				end_Hash.put("tDie_1", System.currentTimeMillis());

				/**
				 * [tDie_1 end ] stop
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
				 * [tDie_1 finally ] start
				 */

				currentComponent = "tDie_1";

				/**
				 * [tDie_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDie_1_SUBPROCESS_STATE", 1);
	}

	public void connectionStatsLogs_CommitProcess(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("connectionStatsLogs_Commit_SUBPROCESS_STATE", 0);

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
				 * [connectionStatsLogs_Commit begin ] start
				 */

				ok_Hash.put("connectionStatsLogs_Commit", false);
				start_Hash.put("connectionStatsLogs_Commit", System.currentTimeMillis());

				currentComponent = "connectionStatsLogs_Commit";

				int tos_count_connectionStatsLogs_Commit = 0;

				/**
				 * [connectionStatsLogs_Commit begin ] stop
				 */

				/**
				 * [connectionStatsLogs_Commit main ] start
				 */

				currentComponent = "connectionStatsLogs_Commit";

				java.sql.Connection conn_connectionStatsLogs_Commit = (java.sql.Connection) globalMap
						.get("conn_connectionStatsLogs");

				if (conn_connectionStatsLogs_Commit != null && !conn_connectionStatsLogs_Commit.isClosed()) {

					conn_connectionStatsLogs_Commit.commit();

				}

				tos_count_connectionStatsLogs_Commit++;

				/**
				 * [connectionStatsLogs_Commit main ] stop
				 */

				/**
				 * [connectionStatsLogs_Commit process_data_begin ] start
				 */

				currentComponent = "connectionStatsLogs_Commit";

				/**
				 * [connectionStatsLogs_Commit process_data_begin ] stop
				 */

				/**
				 * [connectionStatsLogs_Commit process_data_end ] start
				 */

				currentComponent = "connectionStatsLogs_Commit";

				/**
				 * [connectionStatsLogs_Commit process_data_end ] stop
				 */

				/**
				 * [connectionStatsLogs_Commit end ] start
				 */

				currentComponent = "connectionStatsLogs_Commit";

				ok_Hash.put("connectionStatsLogs_Commit", true);
				end_Hash.put("connectionStatsLogs_Commit", System.currentTimeMillis());

				/**
				 * [connectionStatsLogs_Commit end ] stop
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
				 * [connectionStatsLogs_Commit finally ] start
				 */

				currentComponent = "connectionStatsLogs_Commit";

				/**
				 * [connectionStatsLogs_Commit finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("connectionStatsLogs_Commit_SUBPROCESS_STATE", 1);
	}

	public void connectionStatsLogsProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("connectionStatsLogs_SUBPROCESS_STATE", 0);

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
				 * [connectionStatsLogs begin ] start
				 */

				ok_Hash.put("connectionStatsLogs", false);
				start_Hash.put("connectionStatsLogs", System.currentTimeMillis());

				currentComponent = "connectionStatsLogs";

				int tos_count_connectionStatsLogs = 0;

				String url_connectionStatsLogs = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "hr" + "?"
						+ "rewriteBatchedStatements=true";
				String dbUser_connectionStatsLogs = "talend";

				final String decryptedPassword_connectionStatsLogs = routines.system.PasswordEncryptUtil
						.decryptPassword(
								"enc:routine.encryption.key.v1:j5Mhcv8mG7Y+w507scadC048LlaesTUxozptTWOsfGbxrGuPDvkI");
				String dbPwd_connectionStatsLogs = decryptedPassword_connectionStatsLogs;

				java.sql.Connection conn_connectionStatsLogs = null;

				String sharedConnectionName_connectionStatsLogs = "jdbc:mysql://localhost:3306/?"
						+ "_StatsAndLog_Shared_Connection";
				conn_connectionStatsLogs = SharedDBConnection.getDBConnection("com.mysql.cj.jdbc.Driver",
						url_connectionStatsLogs, dbUser_connectionStatsLogs, dbPwd_connectionStatsLogs,
						sharedConnectionName_connectionStatsLogs);
				globalMap.put("conn_connectionStatsLogs", conn_connectionStatsLogs);
				if (null != conn_connectionStatsLogs) {

					conn_connectionStatsLogs.setAutoCommit(false);
				}

				globalMap.put("db_connectionStatsLogs", "hr");

				/**
				 * [connectionStatsLogs begin ] stop
				 */

				/**
				 * [connectionStatsLogs main ] start
				 */

				currentComponent = "connectionStatsLogs";

				tos_count_connectionStatsLogs++;

				/**
				 * [connectionStatsLogs main ] stop
				 */

				/**
				 * [connectionStatsLogs process_data_begin ] start
				 */

				currentComponent = "connectionStatsLogs";

				/**
				 * [connectionStatsLogs process_data_begin ] stop
				 */

				/**
				 * [connectionStatsLogs process_data_end ] start
				 */

				currentComponent = "connectionStatsLogs";

				/**
				 * [connectionStatsLogs process_data_end ] stop
				 */

				/**
				 * [connectionStatsLogs end ] start
				 */

				currentComponent = "connectionStatsLogs";

				ok_Hash.put("connectionStatsLogs", true);
				end_Hash.put("connectionStatsLogs", System.currentTimeMillis());

				/**
				 * [connectionStatsLogs end ] stop
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
				 * [connectionStatsLogs finally ] start
				 */

				currentComponent = "connectionStatsLogs";

				/**
				 * [connectionStatsLogs finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("connectionStatsLogs_SUBPROCESS_STATE", 1);
	}

	public static class row_talendStats_STATSStruct
			implements routines.system.IPersistableRow<row_talendStats_STATSStruct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice09_01 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice09_01 = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public Long system_pid;

		public Long getSystem_pid() {
			return this.system_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String job_repository_id;

		public String getJob_repository_id() {
			return this.job_repository_id;
		}

		public String job_version;

		public String getJob_version() {
			return this.job_version;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message_type;

		public String getMessage_type() {
			return this.message_type;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Long duration;

		public Long getDuration() {
			return this.duration;
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.father_pid = readString(dis);

					this.root_pid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.system_pid = null;
					} else {
						this.system_pid = dis.readLong();
					}

					this.project = readString(dis);

					this.job = readString(dis);

					this.job_repository_id = readString(dis);

					this.job_version = readString(dis);

					this.context = readString(dis);

					this.origin = readString(dis);

					this.message_type = readString(dis);

					this.message = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.duration = null;
					} else {
						this.duration = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.father_pid = readString(dis);

					this.root_pid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.system_pid = null;
					} else {
						this.system_pid = dis.readLong();
					}

					this.project = readString(dis);

					this.job = readString(dis);

					this.job_repository_id = readString(dis);

					this.job_version = readString(dis);

					this.context = readString(dis);

					this.origin = readString(dis);

					this.message_type = readString(dis);

					this.message = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.duration = null;
					} else {
						this.duration = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.root_pid, dos);

				// Long

				if (this.system_pid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.system_pid);
				}

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.job_repository_id, dos);

				// String

				writeString(this.job_version, dos);

				// String

				writeString(this.context, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message_type, dos);

				// String

				writeString(this.message, dos);

				// Long

				if (this.duration == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.duration);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.root_pid, dos);

				// Long

				if (this.system_pid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.system_pid);
				}

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.job_repository_id, dos);

				// String

				writeString(this.job_version, dos);

				// String

				writeString(this.context, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message_type, dos);

				// String

				writeString(this.message, dos);

				// Long

				if (this.duration == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.duration);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",system_pid=" + String.valueOf(system_pid));
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",job_repository_id=" + job_repository_id);
			sb.append(",job_version=" + job_version);
			sb.append(",context=" + context);
			sb.append(",origin=" + origin);
			sb.append(",message_type=" + message_type);
			sb.append(",message=" + message);
			sb.append(",duration=" + String.valueOf(duration));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row_talendStats_STATSStruct other) {

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

	public void talendStats_STATSProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendStats_STATS_SUBPROCESS_STATE", 0);

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

				row_talendStats_STATSStruct row_talendStats_STATS = new row_talendStats_STATSStruct();

				/**
				 * [talendStats_DB begin ] start
				 */

				ok_Hash.put("talendStats_DB", false);
				start_Hash.put("talendStats_DB", System.currentTimeMillis());

				currentVirtualComponent = "talendStats_DB";

				currentComponent = "talendStats_DB";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Main");
				}

				int tos_count_talendStats_DB = 0;

				int nb_line_talendStats_DB = 0;
				int nb_line_update_talendStats_DB = 0;
				int nb_line_inserted_talendStats_DB = 0;
				int nb_line_deleted_talendStats_DB = 0;
				int nb_line_rejected_talendStats_DB = 0;

				int deletedCount_talendStats_DB = 0;
				int updatedCount_talendStats_DB = 0;
				int insertedCount_talendStats_DB = 0;
				int rowsToCommitCount_talendStats_DB = 0;
				int rejectedCount_talendStats_DB = 0;

				String tableName_talendStats_DB = "stats";
				boolean whetherReject_talendStats_DB = false;

				java.util.Calendar calendar_talendStats_DB = java.util.Calendar.getInstance();
				calendar_talendStats_DB.set(1, 0, 1, 0, 0, 0);
				long year1_talendStats_DB = calendar_talendStats_DB.getTime().getTime();
				calendar_talendStats_DB.set(10000, 0, 1, 0, 0, 0);
				long year10000_talendStats_DB = calendar_talendStats_DB.getTime().getTime();
				long date_talendStats_DB;

				java.sql.Connection conn_talendStats_DB = null;
				conn_talendStats_DB = (java.sql.Connection) globalMap.get("conn_connectionStatsLogs");

				int count_talendStats_DB = 0;

				// [%connection%][checktable][tableName]
				String keyCheckTable_talendStats_DB = conn_talendStats_DB + "[checktable]" + "[" + "stats" + "]";

				if (GlobalResource.resourceMap.get(keyCheckTable_talendStats_DB) == null) {// }

					synchronized (GlobalResource.resourceLockMap.get(keyCheckTable_talendStats_DB)) {// }
						if (GlobalResource.resourceMap.get(keyCheckTable_talendStats_DB) == null) {// }
							java.sql.DatabaseMetaData dbMetaData_talendStats_DB = conn_talendStats_DB.getMetaData();
							java.sql.ResultSet rsTable_talendStats_DB = dbMetaData_talendStats_DB.getTables("hr", null,
									null, new String[] { "TABLE" });
							boolean whetherExist_talendStats_DB = false;
							while (rsTable_talendStats_DB.next()) {
								String table_talendStats_DB = rsTable_talendStats_DB.getString("TABLE_NAME");
								if (table_talendStats_DB.equalsIgnoreCase("stats")) {
									whetherExist_talendStats_DB = true;
									break;
								}
							}
							if (!whetherExist_talendStats_DB) {
								try (java.sql.Statement stmtCreate_talendStats_DB = conn_talendStats_DB
										.createStatement()) {
									stmtCreate_talendStats_DB.execute("CREATE TABLE `" + tableName_talendStats_DB
											+ "`(`moment` DATETIME ,`pid` VARCHAR(20)  ,`father_pid` VARCHAR(20)  ,`root_pid` VARCHAR(20)  ,`system_pid` BIGINT(8)  ,`project` VARCHAR(50)  ,`job` VARCHAR(255)  ,`job_repository_id` VARCHAR(255)  ,`job_version` VARCHAR(255)  ,`context` VARCHAR(50)  ,`origin` VARCHAR(255)  ,`message_type` VARCHAR(255)  ,`message` VARCHAR(255)  ,`duration` BIGINT(8)  )");
								}
							}
							GlobalResource.resourceMap.put(keyCheckTable_talendStats_DB, true);
							// {{{
						} // end of if
					} // end synchronized
				}

				String insert_talendStats_DB = "INSERT INTO `" + "stats"
						+ "` (`moment`,`pid`,`father_pid`,`root_pid`,`system_pid`,`project`,`job`,`job_repository_id`,`job_version`,`context`,`origin`,`message_type`,`message`,`duration`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmt_talendStats_DB = null;
				// [%connection%][psmt][tableName]
				String keyPsmt_talendStats_DB = conn_talendStats_DB + "[psmt]" + "[" + "stats" + "]";
				pstmt_talendStats_DB = SharedDBPreparedStatement.getSharedPreparedStatement(conn_talendStats_DB,
						insert_talendStats_DB, keyPsmt_talendStats_DB);
				resourceMap.put("pstmt_talendStats_DB", pstmt_talendStats_DB);

				/**
				 * [talendStats_DB begin ] stop
				 */

				/**
				 * [talendStats_STATS begin ] start
				 */

				ok_Hash.put("talendStats_STATS", false);
				start_Hash.put("talendStats_STATS", System.currentTimeMillis());

				currentVirtualComponent = "talendStats_STATS";

				currentComponent = "talendStats_STATS";

				int tos_count_talendStats_STATS = 0;

				for (StatCatcherUtils.StatCatcherMessage scm : talendStats_STATS.getMessages()) {
					row_talendStats_STATS.pid = pid;
					row_talendStats_STATS.root_pid = rootPid;
					row_talendStats_STATS.father_pid = fatherPid;
					row_talendStats_STATS.project = projectName;
					row_talendStats_STATS.job = jobName;
					row_talendStats_STATS.context = contextStr;
					row_talendStats_STATS.origin = (scm.getOrigin() == null || scm.getOrigin().length() < 1 ? null
							: scm.getOrigin());
					row_talendStats_STATS.message = scm.getMessage();
					row_talendStats_STATS.duration = scm.getDuration();
					row_talendStats_STATS.moment = scm.getMoment();
					row_talendStats_STATS.message_type = scm.getMessageType();
					row_talendStats_STATS.job_version = scm.getJobVersion();
					row_talendStats_STATS.job_repository_id = scm.getJobId();
					row_talendStats_STATS.system_pid = scm.getSystemPid();

					/**
					 * [talendStats_STATS begin ] stop
					 */

					/**
					 * [talendStats_STATS main ] start
					 */

					currentVirtualComponent = "talendStats_STATS";

					currentComponent = "talendStats_STATS";

					tos_count_talendStats_STATS++;

					/**
					 * [talendStats_STATS main ] stop
					 */

					/**
					 * [talendStats_STATS process_data_begin ] start
					 */

					currentVirtualComponent = "talendStats_STATS";

					currentComponent = "talendStats_STATS";

					/**
					 * [talendStats_STATS process_data_begin ] stop
					 */

					/**
					 * [talendStats_DB main ] start
					 */

					currentVirtualComponent = "talendStats_DB";

					currentComponent = "talendStats_DB";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "Main"

						);
					}

					whetherReject_talendStats_DB = false;
					if (row_talendStats_STATS.moment != null) {
						date_talendStats_DB = row_talendStats_STATS.moment.getTime();
						if (date_talendStats_DB < year1_talendStats_DB
								|| date_talendStats_DB >= year10000_talendStats_DB) {
							pstmt_talendStats_DB.setString(1, "0000-00-00 00:00:00");
						} else {
							pstmt_talendStats_DB.setTimestamp(1, new java.sql.Timestamp(date_talendStats_DB));
						}
					} else {
						pstmt_talendStats_DB.setNull(1, java.sql.Types.DATE);
					}

					if (row_talendStats_STATS.pid == null) {
						pstmt_talendStats_DB.setNull(2, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(2, row_talendStats_STATS.pid);
					}

					if (row_talendStats_STATS.father_pid == null) {
						pstmt_talendStats_DB.setNull(3, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(3, row_talendStats_STATS.father_pid);
					}

					if (row_talendStats_STATS.root_pid == null) {
						pstmt_talendStats_DB.setNull(4, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(4, row_talendStats_STATS.root_pid);
					}

					if (row_talendStats_STATS.system_pid == null) {
						pstmt_talendStats_DB.setNull(5, java.sql.Types.INTEGER);
					} else {
						pstmt_talendStats_DB.setLong(5, row_talendStats_STATS.system_pid);
					}

					if (row_talendStats_STATS.project == null) {
						pstmt_talendStats_DB.setNull(6, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(6, row_talendStats_STATS.project);
					}

					if (row_talendStats_STATS.job == null) {
						pstmt_talendStats_DB.setNull(7, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(7, row_talendStats_STATS.job);
					}

					if (row_talendStats_STATS.job_repository_id == null) {
						pstmt_talendStats_DB.setNull(8, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(8, row_talendStats_STATS.job_repository_id);
					}

					if (row_talendStats_STATS.job_version == null) {
						pstmt_talendStats_DB.setNull(9, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(9, row_talendStats_STATS.job_version);
					}

					if (row_talendStats_STATS.context == null) {
						pstmt_talendStats_DB.setNull(10, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(10, row_talendStats_STATS.context);
					}

					if (row_talendStats_STATS.origin == null) {
						pstmt_talendStats_DB.setNull(11, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(11, row_talendStats_STATS.origin);
					}

					if (row_talendStats_STATS.message_type == null) {
						pstmt_talendStats_DB.setNull(12, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(12, row_talendStats_STATS.message_type);
					}

					if (row_talendStats_STATS.message == null) {
						pstmt_talendStats_DB.setNull(13, java.sql.Types.VARCHAR);
					} else {
						pstmt_talendStats_DB.setString(13, row_talendStats_STATS.message);
					}

					if (row_talendStats_STATS.duration == null) {
						pstmt_talendStats_DB.setNull(14, java.sql.Types.INTEGER);
					} else {
						pstmt_talendStats_DB.setLong(14, row_talendStats_STATS.duration);
					}

					try {
						nb_line_talendStats_DB++;
						int processedCount_talendStats_DB = pstmt_talendStats_DB.executeUpdate();
						insertedCount_talendStats_DB += processedCount_talendStats_DB;
						rowsToCommitCount_talendStats_DB += processedCount_talendStats_DB;
					} catch (java.lang.Exception e) {
						globalMap.put("talendStats_DB_ERROR_MESSAGE", e.getMessage());
						whetherReject_talendStats_DB = true;
						System.err.print(e.getMessage());
					}

					tos_count_talendStats_DB++;

					/**
					 * [talendStats_DB main ] stop
					 */

					/**
					 * [talendStats_DB process_data_begin ] start
					 */

					currentVirtualComponent = "talendStats_DB";

					currentComponent = "talendStats_DB";

					/**
					 * [talendStats_DB process_data_begin ] stop
					 */

					/**
					 * [talendStats_DB process_data_end ] start
					 */

					currentVirtualComponent = "talendStats_DB";

					currentComponent = "talendStats_DB";

					/**
					 * [talendStats_DB process_data_end ] stop
					 */

					/**
					 * [talendStats_STATS process_data_end ] start
					 */

					currentVirtualComponent = "talendStats_STATS";

					currentComponent = "talendStats_STATS";

					/**
					 * [talendStats_STATS process_data_end ] stop
					 */

					/**
					 * [talendStats_STATS end ] start
					 */

					currentVirtualComponent = "talendStats_STATS";

					currentComponent = "talendStats_STATS";

				}

				ok_Hash.put("talendStats_STATS", true);
				end_Hash.put("talendStats_STATS", System.currentTimeMillis());

				/**
				 * [talendStats_STATS end ] stop
				 */

				/**
				 * [talendStats_DB end ] start
				 */

				currentVirtualComponent = "talendStats_DB";

				currentComponent = "talendStats_DB";

				if (pstmt_talendStats_DB != null) {

					SharedDBPreparedStatement.releasePreparedStatement(keyPsmt_talendStats_DB);

				}
				resourceMap.put("statementClosed_talendStats_DB", true);

				nb_line_deleted_talendStats_DB = nb_line_deleted_talendStats_DB + deletedCount_talendStats_DB;
				nb_line_update_talendStats_DB = nb_line_update_talendStats_DB + updatedCount_talendStats_DB;
				nb_line_inserted_talendStats_DB = nb_line_inserted_talendStats_DB + insertedCount_talendStats_DB;
				nb_line_rejected_talendStats_DB = nb_line_rejected_talendStats_DB + rejectedCount_talendStats_DB;

				globalMap.put("talendStats_DB_NB_LINE", nb_line_talendStats_DB);
				globalMap.put("talendStats_DB_NB_LINE_UPDATED", nb_line_update_talendStats_DB);
				globalMap.put("talendStats_DB_NB_LINE_INSERTED", nb_line_inserted_talendStats_DB);
				globalMap.put("talendStats_DB_NB_LINE_DELETED", nb_line_deleted_talendStats_DB);
				globalMap.put("talendStats_DB_NB_LINE_REJECTED", nb_line_rejected_talendStats_DB);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Main");
				}

				ok_Hash.put("talendStats_DB", true);
				end_Hash.put("talendStats_DB", System.currentTimeMillis());

				/**
				 * [talendStats_DB end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT",
						"CONNECTION:SUBJOB_OK:talendStats_STATS:sub_ok_talendStats_connectionStatsLogs_Commit", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("sub_ok_talendStats_connectionStatsLogs_Commit", 0, "ok");
			}

			connectionStatsLogs_CommitProcess(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendStats_STATS finally ] start
				 */

				currentVirtualComponent = "talendStats_STATS";

				currentComponent = "talendStats_STATS";

				/**
				 * [talendStats_STATS finally ] stop
				 */

				/**
				 * [talendStats_DB finally ] start
				 */

				currentVirtualComponent = "talendStats_DB";

				currentComponent = "talendStats_DB";

				if (resourceMap.get("statementClosed_talendStats_DB") == null) {
					java.sql.PreparedStatement pstmtToClose_talendStats_DB = null;
					if ((pstmtToClose_talendStats_DB = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_talendStats_DB")) != null) {
						pstmtToClose_talendStats_DB.close();
					}
				}

				/**
				 * [talendStats_DB finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendStats_STATS_SUBPROCESS_STATE", 1);
	}

	public static class row_talendLogs_LOGSStruct
			implements routines.system.IPersistableRow<row_talendLogs_LOGSStruct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice09_01 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice09_01 = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public Integer priority;

		public Integer getPriority() {
			return this.priority;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Integer code;

		public Integer getCode() {
			return this.code;
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice09_01.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice09_01.length == 0) {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice09_01 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice09_01, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice09_01, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice09_01) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",context=" + context);
			sb.append(",priority=" + String.valueOf(priority));
			sb.append(",type=" + type);
			sb.append(",origin=" + origin);
			sb.append(",message=" + message);
			sb.append(",code=" + String.valueOf(code));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row_talendLogs_LOGSStruct other) {

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

	public void talendLogs_LOGSProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendLogs_LOGS_SUBPROCESS_STATE", 0);

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

				row_talendLogs_LOGSStruct row_talendLogs_LOGS = new row_talendLogs_LOGSStruct();

				/**
				 * [talendLogs_DB begin ] start
				 */

				ok_Hash.put("talendLogs_DB", false);
				start_Hash.put("talendLogs_DB", System.currentTimeMillis());

				currentVirtualComponent = "talendLogs_DB";

				currentComponent = "talendLogs_DB";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Main");
				}

				int tos_count_talendLogs_DB = 0;

				int nb_line_talendLogs_DB = 0;
				int nb_line_update_talendLogs_DB = 0;
				int nb_line_inserted_talendLogs_DB = 0;
				int nb_line_deleted_talendLogs_DB = 0;
				int nb_line_rejected_talendLogs_DB = 0;

				int deletedCount_talendLogs_DB = 0;
				int updatedCount_talendLogs_DB = 0;
				int insertedCount_talendLogs_DB = 0;
				int rowsToCommitCount_talendLogs_DB = 0;
				int rejectedCount_talendLogs_DB = 0;

				String tableName_talendLogs_DB = "log";
				boolean whetherReject_talendLogs_DB = false;

				java.util.Calendar calendar_talendLogs_DB = java.util.Calendar.getInstance();
				calendar_talendLogs_DB.set(1, 0, 1, 0, 0, 0);
				long year1_talendLogs_DB = calendar_talendLogs_DB.getTime().getTime();
				calendar_talendLogs_DB.set(10000, 0, 1, 0, 0, 0);
				long year10000_talendLogs_DB = calendar_talendLogs_DB.getTime().getTime();
				long date_talendLogs_DB;

				java.sql.Connection conn_talendLogs_DB = null;
				conn_talendLogs_DB = (java.sql.Connection) globalMap.get("conn_connectionStatsLogs");

				int count_talendLogs_DB = 0;

				// [%connection%][checktable][tableName]
				String keyCheckTable_talendLogs_DB = conn_talendLogs_DB + "[checktable]" + "[" + "log" + "]";

				if (GlobalResource.resourceMap.get(keyCheckTable_talendLogs_DB) == null) {// }

					synchronized (GlobalResource.resourceLockMap.get(keyCheckTable_talendLogs_DB)) {// }
						if (GlobalResource.resourceMap.get(keyCheckTable_talendLogs_DB) == null) {// }
							java.sql.DatabaseMetaData dbMetaData_talendLogs_DB = conn_talendLogs_DB.getMetaData();
							java.sql.ResultSet rsTable_talendLogs_DB = dbMetaData_talendLogs_DB.getTables("hr", null,
									null, new String[] { "TABLE" });
							boolean whetherExist_talendLogs_DB = false;
							while (rsTable_talendLogs_DB.next()) {
								String table_talendLogs_DB = rsTable_talendLogs_DB.getString("TABLE_NAME");
								if (table_talendLogs_DB.equalsIgnoreCase("log")) {
									whetherExist_talendLogs_DB = true;
									break;
								}
							}
							if (!whetherExist_talendLogs_DB) {
								try (java.sql.Statement stmtCreate_talendLogs_DB = conn_talendLogs_DB
										.createStatement()) {
									stmtCreate_talendLogs_DB.execute("CREATE TABLE `" + tableName_talendLogs_DB
											+ "`(`moment` DATETIME ,`pid` VARCHAR(20)  ,`root_pid` VARCHAR(20)  ,`father_pid` VARCHAR(20)  ,`project` VARCHAR(50)  ,`job` VARCHAR(255)  ,`context` VARCHAR(50)  ,`priority` INT(3)  ,`type` VARCHAR(255)  ,`origin` VARCHAR(255)  ,`message` VARCHAR(255)  ,`code` INT(3)  )");
								}
							}
							GlobalResource.resourceMap.put(keyCheckTable_talendLogs_DB, true);
							// {{{
						} // end of if
					} // end synchronized
				}

				String insert_talendLogs_DB = "INSERT INTO `" + "log"
						+ "` (`moment`,`pid`,`root_pid`,`father_pid`,`project`,`job`,`context`,`priority`,`type`,`origin`,`message`,`code`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmt_talendLogs_DB = null;
				// [%connection%][psmt][tableName]
				String keyPsmt_talendLogs_DB = conn_talendLogs_DB + "[psmt]" + "[" + "log" + "]";
				pstmt_talendLogs_DB = SharedDBPreparedStatement.getSharedPreparedStatement(conn_talendLogs_DB,
						insert_talendLogs_DB, keyPsmt_talendLogs_DB);
				resourceMap.put("pstmt_talendLogs_DB", pstmt_talendLogs_DB);

				/**
				 * [talendLogs_DB begin ] stop
				 */

				/**
				 * [talendLogs_LOGS begin ] start
				 */

				ok_Hash.put("talendLogs_LOGS", false);
				start_Hash.put("talendLogs_LOGS", System.currentTimeMillis());

				currentVirtualComponent = "talendLogs_LOGS";

				currentComponent = "talendLogs_LOGS";

				int tos_count_talendLogs_LOGS = 0;

				try {
					for (LogCatcherUtils.LogCatcherMessage lcm : talendLogs_LOGS.getMessages()) {
						row_talendLogs_LOGS.type = lcm.getType();
						row_talendLogs_LOGS.origin = (lcm.getOrigin() == null || lcm.getOrigin().length() < 1 ? null
								: lcm.getOrigin());
						row_talendLogs_LOGS.priority = lcm.getPriority();
						row_talendLogs_LOGS.message = lcm.getMessage();
						row_talendLogs_LOGS.code = lcm.getCode();

						row_talendLogs_LOGS.moment = java.util.Calendar.getInstance().getTime();

						row_talendLogs_LOGS.pid = pid;
						row_talendLogs_LOGS.root_pid = rootPid;
						row_talendLogs_LOGS.father_pid = fatherPid;

						row_talendLogs_LOGS.project = projectName;
						row_talendLogs_LOGS.job = jobName;
						row_talendLogs_LOGS.context = contextStr;

						/**
						 * [talendLogs_LOGS begin ] stop
						 */

						/**
						 * [talendLogs_LOGS main ] start
						 */

						currentVirtualComponent = "talendLogs_LOGS";

						currentComponent = "talendLogs_LOGS";

						tos_count_talendLogs_LOGS++;

						/**
						 * [talendLogs_LOGS main ] stop
						 */

						/**
						 * [talendLogs_LOGS process_data_begin ] start
						 */

						currentVirtualComponent = "talendLogs_LOGS";

						currentComponent = "talendLogs_LOGS";

						/**
						 * [talendLogs_LOGS process_data_begin ] stop
						 */

						/**
						 * [talendLogs_DB main ] start
						 */

						currentVirtualComponent = "talendLogs_DB";

						currentComponent = "talendLogs_DB";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "Main"

							);
						}

						whetherReject_talendLogs_DB = false;
						if (row_talendLogs_LOGS.moment != null) {
							date_talendLogs_DB = row_talendLogs_LOGS.moment.getTime();
							if (date_talendLogs_DB < year1_talendLogs_DB
									|| date_talendLogs_DB >= year10000_talendLogs_DB) {
								pstmt_talendLogs_DB.setString(1, "0000-00-00 00:00:00");
							} else {
								pstmt_talendLogs_DB.setTimestamp(1, new java.sql.Timestamp(date_talendLogs_DB));
							}
						} else {
							pstmt_talendLogs_DB.setNull(1, java.sql.Types.DATE);
						}

						if (row_talendLogs_LOGS.pid == null) {
							pstmt_talendLogs_DB.setNull(2, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(2, row_talendLogs_LOGS.pid);
						}

						if (row_talendLogs_LOGS.root_pid == null) {
							pstmt_talendLogs_DB.setNull(3, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(3, row_talendLogs_LOGS.root_pid);
						}

						if (row_talendLogs_LOGS.father_pid == null) {
							pstmt_talendLogs_DB.setNull(4, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(4, row_talendLogs_LOGS.father_pid);
						}

						if (row_talendLogs_LOGS.project == null) {
							pstmt_talendLogs_DB.setNull(5, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(5, row_talendLogs_LOGS.project);
						}

						if (row_talendLogs_LOGS.job == null) {
							pstmt_talendLogs_DB.setNull(6, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(6, row_talendLogs_LOGS.job);
						}

						if (row_talendLogs_LOGS.context == null) {
							pstmt_talendLogs_DB.setNull(7, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(7, row_talendLogs_LOGS.context);
						}

						if (row_talendLogs_LOGS.priority == null) {
							pstmt_talendLogs_DB.setNull(8, java.sql.Types.INTEGER);
						} else {
							pstmt_talendLogs_DB.setInt(8, row_talendLogs_LOGS.priority);
						}

						if (row_talendLogs_LOGS.type == null) {
							pstmt_talendLogs_DB.setNull(9, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(9, row_talendLogs_LOGS.type);
						}

						if (row_talendLogs_LOGS.origin == null) {
							pstmt_talendLogs_DB.setNull(10, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(10, row_talendLogs_LOGS.origin);
						}

						if (row_talendLogs_LOGS.message == null) {
							pstmt_talendLogs_DB.setNull(11, java.sql.Types.VARCHAR);
						} else {
							pstmt_talendLogs_DB.setString(11, row_talendLogs_LOGS.message);
						}

						if (row_talendLogs_LOGS.code == null) {
							pstmt_talendLogs_DB.setNull(12, java.sql.Types.INTEGER);
						} else {
							pstmt_talendLogs_DB.setInt(12, row_talendLogs_LOGS.code);
						}

						try {
							nb_line_talendLogs_DB++;
							int processedCount_talendLogs_DB = pstmt_talendLogs_DB.executeUpdate();
							insertedCount_talendLogs_DB += processedCount_talendLogs_DB;
							rowsToCommitCount_talendLogs_DB += processedCount_talendLogs_DB;
						} catch (java.lang.Exception e) {
							globalMap.put("talendLogs_DB_ERROR_MESSAGE", e.getMessage());
							whetherReject_talendLogs_DB = true;
							System.err.print(e.getMessage());
						}

						tos_count_talendLogs_DB++;

						/**
						 * [talendLogs_DB main ] stop
						 */

						/**
						 * [talendLogs_DB process_data_begin ] start
						 */

						currentVirtualComponent = "talendLogs_DB";

						currentComponent = "talendLogs_DB";

						/**
						 * [talendLogs_DB process_data_begin ] stop
						 */

						/**
						 * [talendLogs_DB process_data_end ] start
						 */

						currentVirtualComponent = "talendLogs_DB";

						currentComponent = "talendLogs_DB";

						/**
						 * [talendLogs_DB process_data_end ] stop
						 */

						/**
						 * [talendLogs_LOGS process_data_end ] start
						 */

						currentVirtualComponent = "talendLogs_LOGS";

						currentComponent = "talendLogs_LOGS";

						/**
						 * [talendLogs_LOGS process_data_end ] stop
						 */

						/**
						 * [talendLogs_LOGS end ] start
						 */

						currentVirtualComponent = "talendLogs_LOGS";

						currentComponent = "talendLogs_LOGS";

					}
				} catch (Exception e_talendLogs_LOGS) {
					globalMap.put("talendLogs_LOGS_ERROR_MESSAGE", e_talendLogs_LOGS.getMessage());
					logIgnoredError(String.format(
							"talendLogs_LOGS - tLogCatcher failed to process log message(s) due to internal error: %s",
							e_talendLogs_LOGS), e_talendLogs_LOGS);
				}

				ok_Hash.put("talendLogs_LOGS", true);
				end_Hash.put("talendLogs_LOGS", System.currentTimeMillis());

				/**
				 * [talendLogs_LOGS end ] stop
				 */

				/**
				 * [talendLogs_DB end ] start
				 */

				currentVirtualComponent = "talendLogs_DB";

				currentComponent = "talendLogs_DB";

				if (pstmt_talendLogs_DB != null) {

					SharedDBPreparedStatement.releasePreparedStatement(keyPsmt_talendLogs_DB);

				}
				resourceMap.put("statementClosed_talendLogs_DB", true);

				nb_line_deleted_talendLogs_DB = nb_line_deleted_talendLogs_DB + deletedCount_talendLogs_DB;
				nb_line_update_talendLogs_DB = nb_line_update_talendLogs_DB + updatedCount_talendLogs_DB;
				nb_line_inserted_talendLogs_DB = nb_line_inserted_talendLogs_DB + insertedCount_talendLogs_DB;
				nb_line_rejected_talendLogs_DB = nb_line_rejected_talendLogs_DB + rejectedCount_talendLogs_DB;

				globalMap.put("talendLogs_DB_NB_LINE", nb_line_talendLogs_DB);
				globalMap.put("talendLogs_DB_NB_LINE_UPDATED", nb_line_update_talendLogs_DB);
				globalMap.put("talendLogs_DB_NB_LINE_INSERTED", nb_line_inserted_talendLogs_DB);
				globalMap.put("talendLogs_DB_NB_LINE_DELETED", nb_line_deleted_talendLogs_DB);
				globalMap.put("talendLogs_DB_NB_LINE_REJECTED", nb_line_rejected_talendLogs_DB);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Main");
				}

				ok_Hash.put("talendLogs_DB", true);
				end_Hash.put("talendLogs_DB", System.currentTimeMillis());

				/**
				 * [talendLogs_DB end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT",
						"CONNECTION:SUBJOB_OK:talendLogs_LOGS:sub_ok_talendLogs_connectionStatsLogs_Commit", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("sub_ok_talendLogs_connectionStatsLogs_Commit", 0, "ok");
			}

			connectionStatsLogs_CommitProcess(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendLogs_LOGS finally ] start
				 */

				currentVirtualComponent = "talendLogs_LOGS";

				currentComponent = "talendLogs_LOGS";

				/**
				 * [talendLogs_LOGS finally ] stop
				 */

				/**
				 * [talendLogs_DB finally ] start
				 */

				currentVirtualComponent = "talendLogs_DB";

				currentComponent = "talendLogs_DB";

				if (resourceMap.get("statementClosed_talendLogs_DB") == null) {
					java.sql.PreparedStatement pstmtToClose_talendLogs_DB = null;
					if ((pstmtToClose_talendLogs_DB = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_talendLogs_DB")) != null) {
						pstmtToClose_talendLogs_DB.close();
					}
				}

				/**
				 * [talendLogs_DB finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendLogs_LOGS_SUBPROCESS_STATE", 1);
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
		final exercice09_01 exercice09_01Class = new exercice09_01();

		int exitCode = exercice09_01Class.runJobInTOS(args);

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
			java.io.InputStream inContext = exercice09_01.class.getClassLoader()
					.getResourceAsStream("mission_talend/exercice09_01_0_2/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = exercice09_01.class.getClassLoader()
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
		talendStats_STATS.addMessage("begin");

		this.globalResumeTicket = true;// to run tPreJob

		try {
			errorCode = null;
			preStaLogConProcess(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_preStaLogCon) {
			globalMap.put("preStaLogCon_SUBPROCESS_STATE", -1);

			e_preStaLogCon.printStackTrace();

		}

		try {
			talendStats_STATSProcess(globalMap);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tWarn_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tWarn_1) {
			globalMap.put("tWarn_1_SUBPROCESS_STATE", -1);

			e_tWarn_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : exercice09_01");
		}
		talendStats_STATS.addMessage(status == "" ? "end" : status, (end - startTime));
		try {
			talendStats_STATSProcess(globalMap);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
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
		closeSqlDbConnections();

	}

	private void closeSqlDbConnections() {
		try {
			Object obj_conn;
			obj_conn = globalMap.remove("conn_connectionStatsLogs");
			if (null != obj_conn) {
				((java.sql.Connection) obj_conn).close();
			}
		} catch (java.lang.Exception e) {
		}
	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		connections.put("conn_connectionStatsLogs", globalMap.get("conn_connectionStatsLogs"));

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
 * 164403 characters generated by Talend Open Studio for Data Integration on the
 * 11 février 2023 à 12:05:06 GMT+01:00
 ************************************************************************************************/