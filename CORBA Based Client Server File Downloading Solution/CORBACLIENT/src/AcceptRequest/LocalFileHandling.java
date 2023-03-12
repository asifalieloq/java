package AcceptRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalFileHandling {

    public static void writesSettings(ServerSettings softwareSetting) {
        String args = "Settings.bin";
        try {
            System.out.println("Write request");
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(args));
            os.writeObject(softwareSetting);
        } catch (Exception ex) {
            Logger.getLogger(LocalFileHandling.class.getName()).log(Level.SEVERE, (String) null, ex);

        }
    }

    public static ServerSettings readsSettings() throws FileNotFoundException {
        String args = "Settings.bin";
        try {
            FileInputStream s = new FileInputStream(args);
            try {
                ObjectInputStream oi = new ObjectInputStream(s);
                AcceptRequest.softwareSetting = (ServerSettings) oi.readObject();
                s.close();
            } catch (Throwable throwable) {
                try {
                    s.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException ex) {
            Logger.getLogger(LocalFileHandling.class.getName()).log(Level.SEVERE, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LocalFileHandling.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return AcceptRequest.softwareSetting;
    }

}
