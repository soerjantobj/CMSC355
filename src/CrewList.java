/************
 /* Obtaining Crew List
 /* Main - Gathers the input file and the skill needed to retrieve the list that has the crew members if it is not empty.
 /*
 /**********
 /* Input: Parameters of the skill required among the crew members.
 /* Output: An input file containing the list of crew members with the required skill.
 ****************/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrewList {
    public static List<String> getCrewMembers(String role, String inputFile) throws IOException {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role is missing.");
        }

        if (inputFile == null || inputFile.isEmpty()) {
            throw new IllegalArgumentException("Input file is missing.");
        }

        List<String> crewMembersWithRole = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) { // Assuming name and role are the only parts in the line
                    String nameFromFile = parts[0].trim();
                    String roleFromFile = parts[1].trim();
                    if (roleFromFile.equalsIgnoreCase(role)) {
                        crewMembersWithRole.add(nameFromFile);
                    }
                } else {
                    throw new IOException("Invalid format in input file: " + line);
                }
            }
        }

        return crewMembersWithRole;
    }
    public static void main(String[] args){
        String inputFile2 = "src/crew.txt"; // replace with your own input file name
        String skillToSearch = "Captain"; // replace with the skill to search

        try {
            List<String> crewMembersWithSkill = getCrewMembers(skillToSearch, inputFile2);
            if (!crewMembersWithSkill.isEmpty()) {
                System.out.println("Crew members with skill '" + skillToSearch + "': " + crewMembersWithSkill);
            } else {
                System.out.println("No crew members found with skill: " + skillToSearch);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
