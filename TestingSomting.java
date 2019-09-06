package com.org.controler;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
//Required this files from https://github.com/tesseract-ocr/tessdata and it in this location "tessdataPath"
public class TestingSomting {

	final static String tessdataPath = "D:\\tessdata-master\\tessdata-master\\";

	public static void main(String[] args) {
		System.out.println(rgeadImage("D:\\banglatext.gif", "ben"));
	}

	private static String rgeadImage(String Imagepath, String language) {
		BytePointer outText;
		String resultString;
		TessBaseAPI api = new TessBaseAPI();
		if (api.Init(tessdataPath, language) != 0) {
			System.err.println("Could not initialize tesseract.");
			System.exit(1);
		}

		PIX image = pixRead(Imagepath);
		api.SetImage(image);
		// Get OCR result
		outText = api.GetUTF8Text();
		resultString = outText.getString();
		api.End();
		outText.deallocate();
		pixDestroy(image);

		return resultString;
	}

}
