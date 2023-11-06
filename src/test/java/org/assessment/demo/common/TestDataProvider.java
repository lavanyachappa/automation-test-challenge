package org.assessment.demo.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.assessment.demo.utils.Configuration;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

	public static Configuration CONF = new Configuration();

	@DataProvider(name = "odcDataProvider")
	public static Iterator<Object[]> odcDataProvider1(ITestContext context, Method m) throws Exception {

		Data data = m.getAnnotation(Data.class);
		String dataFile = new File(".").getCanonicalPath() + File.separator + data.file();
		int[] rows = data.rows();

		List<Object[]> obj = new ArrayList<Object[]>();

		// No need to process if data file is not present
		if (dataFile == null || dataFile.isEmpty()) {
			return obj.iterator();
		}

		List<String> activeRows = null;
		activeRows = getActiveRows(rows);

		CSVParser parser = new CSVParser(new FileReader(dataFile), CSVFormat.DEFAULT.withHeader());
		obj.addAll(getDataList(parser, activeRows));

		return obj.iterator();
	}

	private static ArrayList<Object[]> getDataList(CSVParser parser, List<String> activeRows) throws IOException {

		ArrayList<Object[]> dataList = new ArrayList<Object[]>();
		Map<String, Integer> header = parser.getHeaderMap();
		List<CSVRecord> list = parser.getRecords();

		for (CSVRecord record : list) {

			if (activeRows == null || activeRows.isEmpty()) {
				dataList.add(new Object[] { getRow(header, record) });
			} else if (activeRows.contains(String.valueOf(record.getRecordNumber()))) {
				dataList.add(new Object[] { getRow(header, record) });
			}
		}
		return dataList;

	}

	// return a list of active rows
	private static List<String> getActiveRows(int[] rows) {

		List<String> aRows = new ArrayList<String>();
		// Let us process the desired rows only
		if (rows != null) {
			for (int row : rows) {
				aRows.add(String.valueOf(row));
			}
		}
		return aRows;

	}

	private static Map<String, String> getRow(Map<String, Integer> header, CSVRecord record) {

		Map<String, String> row = new LinkedHashMap<String, String>();
		for (String key : header.keySet()) {
			String value = record.get(key);
			row.put(key, value);
		}
		row.put("CSVRownumber", String.valueOf(record.getRecordNumber()));
		return row;
	}
}