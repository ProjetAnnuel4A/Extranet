package com.esgi.extranet.system ;

import javax.imageio.ImageIO ;
import java.awt.image.BufferedImage ;
import java.io.* ;
import java.net.ServerSocket ;
import java.net.Socket ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public class UploadSystem
{

    public static void uploadToServer(String imagePath) throws IOException
    {
        Socket soc ;
        BufferedImage img = null ;
        soc = new Socket("localhost", 4000) ;

        try
        {
            img = ImageIO.read(new File(imagePath)) ;
            ByteArrayOutputStream baos = new ByteArrayOutputStream() ;

            ImageIO.write(img, getImageFormat(imagePath), baos) ;
            baos.flush() ;

            byte[] bytes = baos.toByteArray() ;
            baos.close() ;

            OutputStream out = soc.getOutputStream() ;
            DataOutputStream dos = new DataOutputStream(out) ;

            dos.writeInt(bytes.length) ;
            dos.write(bytes, 0, bytes.length) ;

            dos.close() ;
            out.close() ;

        }

        catch (Exception e)
        {
            soc.close() ;
        }

        soc.close() ;
    }

    public static byte[] receiveUpload() throws IOException
    {
        ServerSocket server = null ;
        Socket socket ;
        server = new ServerSocket(4000) ;

        socket = server.accept() ;

        InputStream in = socket.getInputStream() ;
        DataInputStream dis = new DataInputStream(in) ;

        int len = dis.readInt() ;

        byte[] data = new byte[len] ;
        dis.readFully(data) ;
        dis.close() ;
        in.close() ;

        return data ;
    }

    public static String getImageFormat(String imagePath)
    {
        String imageExtension = imagePath.substring(imagePath.lastIndexOf(".") + 1, imagePath.length()) ;

        return imageExtension ;
    }

    public String saveImage(byte[] data)
    {
        String imageServerPath = "" ;

        // Do something

        return imageServerPath ;
    }

}
