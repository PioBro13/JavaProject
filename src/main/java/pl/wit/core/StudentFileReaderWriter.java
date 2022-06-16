package pl.wit.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * File reader and writer service
 */
class StudentFileReaderWriter {

	/**
	 * Mapper for Student object
	 */
	private final ObjectMapper objectMapper;

	/**
	 * Constructor of reader and writer
	 */
	StudentFileReaderWriter() {
		this.objectMapper = new ObjectMapper()
				.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
				.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	/**
	 * Saving data from table to file
	 * @param students list of student objects
	 * @param filename name of file to which data will be saved
	 * @throws IOException wrong data
	 */
	void save(List<Student> students, String filename) throws IOException {
		String json = objectMapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(students);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(json);
			writer.flush();
		}
	}

	/**
	 * Loading data from given file
	 * @param filename name of given file
	 * @return list of students from file
	 * @throws IOException wrong data
	 */
	List<Student> load(String filename) throws IOException {
		String json = new String(Files.readAllBytes(Paths.get(filename)));
		return Arrays.asList(objectMapper.readValue(json, Student[].class));
	}
}
