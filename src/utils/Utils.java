package utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * provides a method to read a file containing integers and stores them in an array
 * @author Dex
 *
 */
public class Utils {
	
	public static Integer[] readArray(String filePath) {
		List<Integer> result = new ArrayList<>();
		try(Stream<String> stream = Files.lines(Paths.get(filePath))) {
			result = stream
					.map(Integer::valueOf)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toArray(new Integer[result.size()]);
	}
}
