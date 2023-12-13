/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;


public class FileChoose {

    public static String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        String os = System.getProperty("os.name").toLowerCase();
        File deFile = new File(".\\src\\anh\\");
        if (os.contains("mac")) {
            deFile = new File("./src/anh/");
        }
        fileChooser.setSelectedFile(deFile);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File sFile = fileChooser.getSelectedFile();
            String paString = sFile.getPath();
            int last = paString.lastIndexOf("\\");
            String path = paString.substring(last + 1);
            System.out.println("file : ");
            System.out.println(path);
            saveImage(sFile,path);
            return path;
//            Image orImage = new ImageIcon(paString).getImage();
//            Image resize = orImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//            txtanh.setIcon(new ImageIcon(resize));
        }
        return null;
    }

    public static void saveImage(File selectedFile,String name) {
        // Set the desired directory and file name
        String destinationDirectory = ".\\src\\anh\\";
        
        String fileName = name; // You can customize the file name

        Path destinationPath = Path.of(destinationDirectory, fileName);

        try {
            // Copy the selected file to the destination directory with the specified file name
            Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
