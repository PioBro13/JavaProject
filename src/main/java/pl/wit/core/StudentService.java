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

public class StudentService {

	private final StudentRepository studentRepository;

	private final ObjectMapper objectMapper;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		this.objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	public void save(String filename) throws IOException {
		String json = objectMapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(studentRepository.getAllStudents());
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(json);
			writer.flush();
		}
	}

	public void load(String filename) throws IOException {
		String json = new String(Files.readAllBytes(Paths.get(filename)));
		List<Student> students = Arrays.asList(objectMapper.readValue(json, Student[].class));
		studentRepository.load(students);
	}
}
