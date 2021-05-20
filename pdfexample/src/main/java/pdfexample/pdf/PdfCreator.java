package pdfexample.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCreator {

	public Document createDocument(File file) throws IOException, URISyntaxException, DocumentException {
		Document document = new Document();
		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			PdfWriter pdfWriter = PdfWriter.getInstance(document, fileStream);

			pdfWriter.setEncryption("user".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_PRINTING,
					PdfWriter.ENCRYPTION_AES_256);

			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello World", font);

			document.add(chunk);

			Path path = Paths.get(getClass().getResource("/img/ok.png").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.setWidthPercentage((float) 0.2);
			img.setScaleToFitHeight(true);
			document.add(img);

			PdfPTable table = new PdfPTable(3);
			addTableHeader(table);
			addRows(table);
			addCustomRows(table);

			document.add(table);

			document.close();
		}

		return document;
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("column header 1", "column header 2", "column header 3").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table) {
		table.addCell("row 1, col 1");
		table.addCell("row 1, col 2");
		table.addCell("row 1, col 3");
	}

	private void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
		Path path = Paths.get(getClass().getResource("/img/userlogin.png").toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		img.scalePercent(10);

		PdfPCell imageCell = new PdfPCell(img);
		table.addCell(imageCell);

		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(horizontalAlignCell);

		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
		verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		table.addCell(verticalAlignCell);
	}

	public static void main(String[] args)
			throws MalformedURLException, IOException, URISyntaxException, DocumentException {
		new PdfCreator().createDocument(new File("C:\\Users\\jose.m.prieto.villar\\Desktop\\temp2.pdf"));
	}

}
