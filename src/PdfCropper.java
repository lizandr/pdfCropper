/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfRectangle;
import com.itextpdf.text.pdf.PdfStamper;

/**
 *
 * @author lizandro
 */
public class PdfCropper {

   /**
    * Manipulates a PDF file src with the file dest as result
    *
    * @param src the original PDF
    * @param dest the resulting PDF
    * @throws IOException
    * @throws DocumentException
    */
   public void manipulatePdf(String src, int page, int llx, int lly, int urx, int ury, String dest)
           throws IOException, DocumentException {
      PdfReader reader=new PdfReader(src);
      reader.selectPages(Integer.toString(page));
      PdfDictionary pageDict;
      PdfRectangle rect=new PdfRectangle(llx, lly, urx, ury);
      pageDict=reader.getPageN(1);
      pageDict.put(PdfName.CROPBOX, rect);
      PdfStamper stamper=new PdfStamper(reader, new FileOutputStream(dest));
      stamper.close();
   }
   /**
    * Main method.
    *
    * @param args no arguments needed
    * @throws DocumentException
    * @throws IOException
    */
   public static void main(String[] args) throws IOException, DocumentException {
      new PdfCropper().manipulatePdf(args[0], Integer.parseInt(args[1]), 
              Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), 
              args[6]);
   }
}
