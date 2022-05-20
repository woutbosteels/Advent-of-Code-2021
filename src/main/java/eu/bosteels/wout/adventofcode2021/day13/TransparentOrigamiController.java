package eu.bosteels.wout.adventofcode2021.day13;

import eu.bosteels.wout.adventofcode2021.day13.model.TransparantPaper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

@Component
public class TransparentOrigamiController implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(TransparentOrigamiController.class);

    private final String TEST_INPUT_FILE_LOCATION = "day13/test-input";
    private final String INPUT_FILE_LOCATION = "day13/input";

    private String readInputFile(String fileLocation) throws IOException {
        Resource resource = new ClassPathResource(fileLocation);
        FileInputStream file = new FileInputStream(resource.getFile());
        return new String(file.readAllBytes());
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(readInputFile(INPUT_FILE_LOCATION));
        TransparantPaper paper = new TransparantPaper(scanner);


        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("fold along ")) {
                if (line.charAt(11) == 'x'){
                    paper.foldAlongX(Integer.parseInt(line.substring(13)));
                }
                else if (line.charAt(11) == 'y'){
                    paper.foldAlongY(Integer.parseInt(line.substring(13)));
                }
                System.out.println(paper.getDotCount());

            }
        }
        System.out.println(paper);
    }

}
