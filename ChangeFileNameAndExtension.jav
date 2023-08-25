import java.io.File;

public class ChangeFileNameAndExtension {
    public static void main(String[] args) {
        // Provide the current file path
        String currentFilePath = "path/to/oldfile.txt";
        
        // Provide the new file name and extension
        String newFileName = "newfile";
        String newExtension = "dat";
        
        changeFileNameAndExtension(currentFilePath, newFileName, newExtension);
    }

    public static void changeFileNameAndExtension(String currentFilePath, String newFileName, String newExtension) {
        File currentFile = new File(currentFilePath);
        if (currentFile.exists()) {
            // Get the directory of the current file
            String parentDirectory = currentFile.getParent();
            
            // Create a new File instance with the new file name and extension
            File newFile = new File(parentDirectory, newFileName + "." + newExtension);
            
            // Rename the file
            if (currentFile.renameTo(newFile)) {
                System.out.println("File name and extension changed successfully.");
            } else {
                System.out.println("Failed to change file name and extension.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
