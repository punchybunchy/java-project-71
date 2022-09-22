package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Parser {

    public static boolean fileExistCheck(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath();
        if (!((filePath.endsWith(".yaml")) || (filePath.endsWith(".json")))) {
            throw new Exception("File exists, but has not valid extension");
        } else if (Files.exists(path)) {
            if (Files.isRegularFile(path)) {
                System.out.println("File exists!");
                return true;
            }
            if (Files.isDirectory(path)) {
                System.out.println("File exists, but it is a directory.");
            }
        } else {
            System.out.println("File doesn't exist");
        }
        throw new Exception("File doesn't exist");
    }

    public static Map<String, Object> parserFilesToMap(String filePath) throws Exception {
        if (fileExistCheck(filePath)) {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            Map<String, Object> map = objectMapper.readValue(file, new TypeReference<>() { });
            return map;
        }
        throw new Exception("Check if your file path is correct");
    }
}