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

package mission_talend.exercice03_03_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.MyRoutine;
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

//the import part of tJavaFlex_1
//import java.util.List;

//the import part of tJavaRow_1
//import java.util.List;

//the import part of tJava_1
//import java.util.List;

@SuppressWarnings("unused")

/**
 * Job: exercice03_03 Purpose: Utilisation des composants Java<br>
 * Description: Utiliser des composants Java dans un job <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status TEST
 */
public class exercice03_03 implements TalendJob {

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
	private final String jobName = "exercice03_03";
	private final String projectName = "MISSION_TALEND";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

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
					exercice03_03.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(exercice03_03.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputFullRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaFlex_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_1_error(Exception exception, String errorComponent,
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

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJava_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tJava_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputFullRow_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tJava_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice03_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice03_03 = new byte[0];

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

				try {

					int length = 0;

				}

				finally {
				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

				try {

					int length = 0;

				}

				finally {
				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

			}

			finally {
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

			}

			finally {
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
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
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice03_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice03_03 = new byte[0];

		public String line;

		public String getLine() {
			return this.line;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

				try {

					int length = 0;

					this.line = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

				try {

					int length = 0;

					this.line = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.line, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.line, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("line=" + line);
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

	public void tFileInputFullRow_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputFullRow_1_SUBPROCESS_STATE", 0);

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
				row2Struct row2 = new row2Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

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
				 * [tJavaFlex_1 begin ] start
				 */

				ok_Hash.put("tJavaFlex_1", false);
				start_Hash.put("tJavaFlex_1", System.currentTimeMillis());

				currentComponent = "tJavaFlex_1";

				int tos_count_tJavaFlex_1 = 0;

// start part of your Java code
				String query = new String();

				/**
				 * [tJavaFlex_1 begin ] stop
				 */

				/**
				 * [tFileInputFullRow_1 begin ] start
				 */

				ok_Hash.put("tFileInputFullRow_1", false);
				start_Hash.put("tFileInputFullRow_1", System.currentTimeMillis());

				currentComponent = "tFileInputFullRow_1";

				int tos_count_tFileInputFullRow_1 = 0;

				org.talend.fileprocess.FileInputDelimited fid_tFileInputFullRow_1 = null;

				try {// }
					fid_tFileInputFullRow_1 = new org.talend.fileprocess.FileInputDelimited(
							"C:/Users/Abdelhak Pedro/Documents/Talend_Mission/sql/query.sql", "ISO-8859-15", "", "\n",
							true, 0, 0, -1, -1, false);
					while (fid_tFileInputFullRow_1.nextRecord()) {// }
						row1 = null;
						boolean whetherReject_tFileInputFullRow_1 = false;
						row1 = new row1Struct();
						row1.line = fid_tFileInputFullRow_1.get(0);

						/**
						 * [tFileInputFullRow_1 begin ] stop
						 */

						/**
						 * [tFileInputFullRow_1 main ] start
						 */

						currentComponent = "tFileInputFullRow_1";

						tos_count_tFileInputFullRow_1++;

						/**
						 * [tFileInputFullRow_1 main ] stop
						 */

						/**
						 * [tFileInputFullRow_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputFullRow_1";

						/**
						 * [tFileInputFullRow_1 process_data_begin ] stop
						 */

						/**
						 * [tJavaFlex_1 main ] start
						 */

						currentComponent = "tJavaFlex_1";

// here is the main part of the component,
// a piece of code executed in the row
// loop
						query = query + "\n" + row1.line;

//System.out.println(query);

						tos_count_tJavaFlex_1++;

						/**
						 * [tJavaFlex_1 main ] stop
						 */

						/**
						 * [tJavaFlex_1 process_data_begin ] start
						 */

						currentComponent = "tJavaFlex_1";

						/**
						 * [tJavaFlex_1 process_data_begin ] stop
						 */

						/**
						 * [tLogRow_1 main ] start
						 */

						currentComponent = "tLogRow_1";

///////////////////////		

						strBuffer_tLogRow_1 = new StringBuilder();

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

						/**
						 * [tJavaFlex_1 process_data_end ] start
						 */

						currentComponent = "tJavaFlex_1";

						/**
						 * [tJavaFlex_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputFullRow_1 process_data_end ] start
						 */

						currentComponent = "tFileInputFullRow_1";

						/**
						 * [tFileInputFullRow_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputFullRow_1 end ] start
						 */

						currentComponent = "tFileInputFullRow_1";

					}
				} finally {
					if (fid_tFileInputFullRow_1 != null) {
						fid_tFileInputFullRow_1.close();
					}
				}
				globalMap.put("tFileInputFullRow_1_NB_LINE", fid_tFileInputFullRow_1.getRowNumber());

				ok_Hash.put("tFileInputFullRow_1", true);
				end_Hash.put("tFileInputFullRow_1", System.currentTimeMillis());

				/**
				 * [tFileInputFullRow_1 end ] stop
				 */

				/**
				 * [tJavaFlex_1 end ] start
				 */

				currentComponent = "tJavaFlex_1";

// end of the component, outside/closing the loop
				globalMap.put("query", query);

//System.out.println(query);   

				ok_Hash.put("tJavaFlex_1", true);
				end_Hash.put("tJavaFlex_1", System.currentTimeMillis());

				/**
				 * [tJavaFlex_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputFullRow_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			tDBInput_1Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputFullRow_1 finally ] start
				 */

				currentComponent = "tFileInputFullRow_1";

				/**
				 * [tFileInputFullRow_1 finally ] stop
				 */

				/**
				 * [tJavaFlex_1 finally ] start
				 */

				currentComponent = "tJavaFlex_1";

				/**
				 * [tJavaFlex_1 finally ] stop
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

		globalMap.put("tFileInputFullRow_1_SUBPROCESS_STATE", 1);
	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice03_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice03_03 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

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

		public Boolean isValid;

		public Boolean getIsValid() {
			return this.isValid;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.EmployeeID == null) ? 0 : this.EmployeeID.hashCode());

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
			final row5Struct other = (row5Struct) obj;

			if (this.EmployeeID == null) {
				if (other.EmployeeID != null)
					return false;

			} else if (!this.EmployeeID.equals(other.EmployeeID))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.EmployeeID = this.EmployeeID;
			other.FirstName = this.FirstName;
			other.LastName = this.LastName;
			other.Email = this.Email;
			other.PhoneNumber = this.PhoneNumber;
			other.HireDate = this.HireDate;
			other.JobCode = this.JobCode;
			other.Salary = this.Salary;
			other.CommissionPercentage = this.CommissionPercentage;
			other.ManagerID = this.ManagerID;
			other.DepartmentID = this.DepartmentID;
			other.isValid = this.isValid;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.EmployeeID = this.EmployeeID;

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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

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

					length = dis.readByte();
					if (length == -1) {
						this.isValid = null;
					} else {
						this.isValid = dis.readBoolean();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

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

					length = dis.readByte();
					if (length == -1) {
						this.isValid = null;
					} else {
						this.isValid = dis.readBoolean();
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

				// Boolean

				if (this.isValid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.isValid);
				}

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

				// Boolean

				if (this.isValid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.isValid);
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
			sb.append(",isValid=" + String.valueOf(isValid));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.EmployeeID, other.EmployeeID);
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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice03_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice03_03 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

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

		public Boolean isValid;

		public Boolean getIsValid() {
			return this.isValid;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.EmployeeID == null) ? 0 : this.EmployeeID.hashCode());

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
			final row4Struct other = (row4Struct) obj;

			if (this.EmployeeID == null) {
				if (other.EmployeeID != null)
					return false;

			} else if (!this.EmployeeID.equals(other.EmployeeID))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.EmployeeID = this.EmployeeID;
			other.FirstName = this.FirstName;
			other.LastName = this.LastName;
			other.Email = this.Email;
			other.PhoneNumber = this.PhoneNumber;
			other.HireDate = this.HireDate;
			other.JobCode = this.JobCode;
			other.Salary = this.Salary;
			other.CommissionPercentage = this.CommissionPercentage;
			other.ManagerID = this.ManagerID;
			other.DepartmentID = this.DepartmentID;
			other.isValid = this.isValid;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.EmployeeID = this.EmployeeID;

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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

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

					length = dis.readByte();
					if (length == -1) {
						this.isValid = null;
					} else {
						this.isValid = dis.readBoolean();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

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

					length = dis.readByte();
					if (length == -1) {
						this.isValid = null;
					} else {
						this.isValid = dis.readBoolean();
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

				// Boolean

				if (this.isValid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.isValid);
				}

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

				// Boolean

				if (this.isValid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.isValid);
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
			sb.append(",isValid=" + String.valueOf(isValid));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.EmployeeID, other.EmployeeID);
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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_MISSION_TALEND_exercice03_03 = new byte[0];
		static byte[] commonByteArray_MISSION_TALEND_exercice03_03 = new byte[0];

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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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
				if (length > commonByteArray_MISSION_TALEND_exercice03_03.length) {
					if (length < 1024 && commonByteArray_MISSION_TALEND_exercice03_03.length == 0) {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[1024];
					} else {
						commonByteArray_MISSION_TALEND_exercice03_03 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MISSION_TALEND_exercice03_03, 0, length);
				strReturn = new String(commonByteArray_MISSION_TALEND_exercice03_03, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

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

			synchronized (commonByteArrayLock_MISSION_TALEND_exercice03_03) {

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

				row3Struct row3 = new row3Struct();
				row4Struct row4 = new row4Struct();
				row5Struct row5 = new row5Struct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				int tos_count_tDBOutput_1 = 0;

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 11 && true) {
					throw new RuntimeException("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "employees";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "hr" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_1 = "talend";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:zIMu7KJRrVh/n0hGCMLXFxuZQ5+NPrjpjFALjdZ9wXZoyXA2frYr");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;
				int batchSize_tDBOutput_1 = 10000;
				int batchSizeCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				String update_tDBOutput_1 = "UPDATE `" + "employees"
						+ "` SET `FirstName` = ?,`LastName` = ?,`Email` = ?,`PhoneNumber` = ?,`HireDate` = ?,`JobCode` = ?,`Salary` = ?,`CommissionPercentage` = ?,`ManagerID` = ?,`DepartmentID` = ? WHERE `EmployeeID` = ?";

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tFilterRow_1 begin ] start
				 */

				ok_Hash.put("tFilterRow_1", false);
				start_Hash.put("tFilterRow_1", System.currentTimeMillis());

				currentComponent = "tFilterRow_1";

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
				 * [tJavaRow_1 begin ] start
				 */

				ok_Hash.put("tJavaRow_1", false);
				start_Hash.put("tJavaRow_1", System.currentTimeMillis());

				currentComponent = "tJavaRow_1";

				int tos_count_tJavaRow_1 = 0;

				int nb_line_tJavaRow_1 = 0;

				/**
				 * [tJavaRow_1 begin ] stop
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
						"enc:routine.encryption.key.v1:bdnIrUqe6kavRMxxFrHuQmHa0axT/KXbPDLnAoLfRn72UTlumZbg");

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

				String dbquery_tDBInput_1 = ((String) globalMap.get("query"));

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
							row3.EmployeeID = null;
						} else {

							row3.EmployeeID = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								row3.EmployeeID = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row3.FirstName = null;
						} else {

							row3.FirstName = routines.system.JDBCUtil.getString(rs_tDBInput_1, 2, false);
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row3.LastName = null;
						} else {

							row3.LastName = routines.system.JDBCUtil.getString(rs_tDBInput_1, 3, false);
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row3.Email = null;
						} else {

							row3.Email = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row3.PhoneNumber = null;
						} else {

							row3.PhoneNumber = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row3.HireDate = null;
						} else {

							if (rs_tDBInput_1.getString(6) != null) {
								String dateString_tDBInput_1 = rs_tDBInput_1.getString(6);
								if (!("0000-00-00").equals(dateString_tDBInput_1)
										&& !("0000-00-00 00:00:00").equals(dateString_tDBInput_1)) {
									row3.HireDate = rs_tDBInput_1.getTimestamp(6);
								} else {
									row3.HireDate = (java.util.Date) year0_tDBInput_1.clone();
								}
							} else {
								row3.HireDate = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row3.JobCode = null;
						} else {

							row3.JobCode = routines.system.JDBCUtil.getString(rs_tDBInput_1, 7, false);
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row3.Salary = null;
						} else {

							row3.Salary = rs_tDBInput_1.getDouble(8);
							if (rs_tDBInput_1.wasNull()) {
								row3.Salary = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row3.CommissionPercentage = null;
						} else {

							row3.CommissionPercentage = rs_tDBInput_1.getDouble(9);
							if (rs_tDBInput_1.wasNull()) {
								row3.CommissionPercentage = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row3.ManagerID = null;
						} else {

							row3.ManagerID = rs_tDBInput_1.getInt(10);
							if (rs_tDBInput_1.wasNull()) {
								row3.ManagerID = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row3.DepartmentID = null;
						} else {

							row3.DepartmentID = rs_tDBInput_1.getInt(11);
							if (rs_tDBInput_1.wasNull()) {
								row3.DepartmentID = null;
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
						 * [tJavaRow_1 main ] start
						 */

						currentComponent = "tJavaRow_1";

						// Code généré selon les schémas d'entrée et de sortie
						row4.EmployeeID = row3.EmployeeID;
						row4.FirstName = row3.FirstName;
						row4.LastName = row3.LastName;
						row4.Email = row3.Email;
						row4.PhoneNumber = row3.PhoneNumber;
						row4.HireDate = row3.HireDate;
						row4.JobCode = row3.JobCode;
						row4.Salary = row3.Salary;
						row4.CommissionPercentage = row3.CommissionPercentage;
						row4.ManagerID = row3.ManagerID;
						row4.DepartmentID = row3.DepartmentID;
						row4.isValid = MyRoutine.isValidEmail(row3.Email);

						nb_line_tJavaRow_1++;

						tos_count_tJavaRow_1++;

						/**
						 * [tJavaRow_1 main ] stop
						 */

						/**
						 * [tJavaRow_1 process_data_begin ] start
						 */

						currentComponent = "tJavaRow_1";

						/**
						 * [tJavaRow_1 process_data_begin ] stop
						 */

						/**
						 * [tFilterRow_1 main ] start
						 */

						currentComponent = "tFilterRow_1";

						row5 = null;
						Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
						ope_tFilterRow_1.matches((row4.isValid == null ? false : row4.isValid.compareTo(true) == 0),
								"isValid.compareTo(true) == 0 failed");

						if (ope_tFilterRow_1.getMatchFlag()) {
							if (row5 == null) {
								row5 = new row5Struct();
							}
							row5.EmployeeID = row4.EmployeeID;
							row5.FirstName = row4.FirstName;
							row5.LastName = row4.LastName;
							row5.Email = row4.Email;
							row5.PhoneNumber = row4.PhoneNumber;
							row5.HireDate = row4.HireDate;
							row5.JobCode = row4.JobCode;
							row5.Salary = row4.Salary;
							row5.CommissionPercentage = row4.CommissionPercentage;
							row5.ManagerID = row4.ManagerID;
							row5.DepartmentID = row4.DepartmentID;
							row5.isValid = row4.isValid;
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
// Start of branch "row5"
						if (row5 != null) {

							/**
							 * [tDBOutput_1 main ] start
							 */

							currentComponent = "tDBOutput_1";

							whetherReject_tDBOutput_1 = false;
							if (row5.FirstName == null) {
								pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(1, row5.FirstName);
							}

							if (row5.LastName == null) {
								pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(2, row5.LastName);
							}

							if (row5.Email == null) {
								pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(3, row5.Email);
							}

							if (row5.PhoneNumber == null) {
								pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(4, row5.PhoneNumber);
							}

							if (row5.HireDate != null) {
								date_tDBOutput_1 = row5.HireDate.getTime();
								if (date_tDBOutput_1 < year1_tDBOutput_1 || date_tDBOutput_1 >= year10000_tDBOutput_1) {
									pstmt_tDBOutput_1.setString(5, "0000-00-00 00:00:00");
								} else {
									pstmt_tDBOutput_1.setTimestamp(5, new java.sql.Timestamp(date_tDBOutput_1));
								}
							} else {
								pstmt_tDBOutput_1.setNull(5, java.sql.Types.DATE);
							}

							if (row5.JobCode == null) {
								pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_1.setString(6, row5.JobCode);
							}

							if (row5.Salary == null) {
								pstmt_tDBOutput_1.setNull(7, java.sql.Types.DOUBLE);
							} else {
								pstmt_tDBOutput_1.setDouble(7, row5.Salary);
							}

							if (row5.CommissionPercentage == null) {
								pstmt_tDBOutput_1.setNull(8, java.sql.Types.DOUBLE);
							} else {
								pstmt_tDBOutput_1.setDouble(8, row5.CommissionPercentage);
							}

							if (row5.ManagerID == null) {
								pstmt_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(9, row5.ManagerID);
							}

							if (row5.DepartmentID == null) {
								pstmt_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(10, row5.DepartmentID);
							}

							if (row5.EmployeeID == null) {
								pstmt_tDBOutput_1.setNull(11 + count_tDBOutput_1, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(11 + count_tDBOutput_1, row5.EmployeeID);
							}

							pstmt_tDBOutput_1.addBatch();
							nb_line_tDBOutput_1++;

							batchSizeCounter_tDBOutput_1++;
							if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
								try {
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									updatedCount_tDBOutput_1 += countSum_tDBOutput_1;
									batchSizeCounter_tDBOutput_1 = 0;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									updatedCount_tDBOutput_1 += countSum_tDBOutput_1;
									System.err.println(e.getMessage());
								}

							}
							commitCounter_tDBOutput_1++;

							if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

								try {
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									updatedCount_tDBOutput_1 += countSum_tDBOutput_1;
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
										countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}
									rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
									updatedCount_tDBOutput_1 += countSum_tDBOutput_1;
									System.err.println(e.getMessage());

								}
								if (rowsToCommitCount_tDBOutput_1 != 0) {
								}
								conn_tDBOutput_1.commit();
								if (rowsToCommitCount_tDBOutput_1 != 0) {
									rowsToCommitCount_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1 = 0;

							}

							tos_count_tDBOutput_1++;

							/**
							 * [tDBOutput_1 main ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_end ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_end ] stop
							 */

						} // End of branch "row5"

						/**
						 * [tFilterRow_1 process_data_end ] start
						 */

						currentComponent = "tFilterRow_1";

						/**
						 * [tFilterRow_1 process_data_end ] stop
						 */

						/**
						 * [tJavaRow_1 process_data_end ] start
						 */

						currentComponent = "tJavaRow_1";

						/**
						 * [tJavaRow_1 process_data_end ] stop
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
				 * [tJavaRow_1 end ] start
				 */

				currentComponent = "tJavaRow_1";

				globalMap.put("tJavaRow_1_NB_LINE", nb_line_tJavaRow_1);

				ok_Hash.put("tJavaRow_1", true);
				end_Hash.put("tJavaRow_1", System.currentTimeMillis());

				/**
				 * [tJavaRow_1 end ] stop
				 */

				/**
				 * [tFilterRow_1 end ] start
				 */

				currentComponent = "tFilterRow_1";

				globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);

				ok_Hash.put("tFilterRow_1", true);
				end_Hash.put("tFilterRow_1", System.currentTimeMillis());

				/**
				 * [tFilterRow_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (pstmt_tDBOutput_1 != null) {
						int countSum_tDBOutput_1 = 0;

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						updatedCount_tDBOutput_1 += countSum_tDBOutput_1;

					}
				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					updatedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}

				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tDBInput_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			tJava_1Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tJavaRow_1 finally ] start
				 */

				currentComponent = "tJavaRow_1";

				/**
				 * [tJavaRow_1 finally ] stop
				 */

				/**
				 * [tFilterRow_1 finally ] start
				 */

				currentComponent = "tFilterRow_1";

				/**
				 * [tFilterRow_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
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

	public void tJava_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tJava_1_SUBPROCESS_STATE", 0);

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
				 * [tJava_1 begin ] start
				 */

				ok_Hash.put("tJava_1", false);
				start_Hash.put("tJava_1", System.currentTimeMillis());

				currentComponent = "tJava_1";

				int tos_count_tJava_1 = 0;

				System.out.println(((String) globalMap.get("tDBInput_1_QUERY")));

				/**
				 * [tJava_1 begin ] stop
				 */

				/**
				 * [tJava_1 main ] start
				 */

				currentComponent = "tJava_1";

				tos_count_tJava_1++;

				/**
				 * [tJava_1 main ] stop
				 */

				/**
				 * [tJava_1 process_data_begin ] start
				 */

				currentComponent = "tJava_1";

				/**
				 * [tJava_1 process_data_begin ] stop
				 */

				/**
				 * [tJava_1 process_data_end ] start
				 */

				currentComponent = "tJava_1";

				/**
				 * [tJava_1 process_data_end ] stop
				 */

				/**
				 * [tJava_1 end ] start
				 */

				currentComponent = "tJava_1";

				ok_Hash.put("tJava_1", true);
				end_Hash.put("tJava_1", System.currentTimeMillis());

				/**
				 * [tJava_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tJava_1 finally ] start
				 */

				currentComponent = "tJava_1";

				/**
				 * [tJava_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tJava_1_SUBPROCESS_STATE", 1);
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
		final exercice03_03 exercice03_03Class = new exercice03_03();

		int exitCode = exercice03_03Class.runJobInTOS(args);

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
			java.io.InputStream inContext = exercice03_03.class.getClassLoader()
					.getResourceAsStream("mission_talend/exercice03_03_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = exercice03_03.class.getClassLoader()
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
			tFileInputFullRow_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputFullRow_1) {
			globalMap.put("tFileInputFullRow_1_SUBPROCESS_STATE", -1);

			e_tFileInputFullRow_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : exercice03_03");
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
 * 114776 characters generated by Talend Open Studio for Data Integration on the
 * 8 février 2023 à 17:34:04 GMT+01:00
 ************************************************************************************************/