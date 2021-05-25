package pdfexample.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public class IText7PdfCreator {

	public Document createDocument(File file)
			throws URISyntaxException, DocumentException, FileNotFoundException, IOException {
		Document doc = null;
		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			PdfWriter pdfWriter = new PdfWriter(fileStream,
					new WriterProperties().setStandardEncryption("user".getBytes(), "1234".getBytes(),
							EncryptionConstants.ALLOW_PRINTING,
							EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA));
			PdfDocument pdfDoc = new PdfDocument(pdfWriter);
			doc = new Document(pdfDoc);

			PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
			Paragraph paragraph = new Paragraph("Hello iText advanced example \n");
			paragraph.setFontSize(24);
			paragraph.setTextAlignment(TextAlignment.CENTER);
			paragraph.setFont(font);

			doc.add(paragraph);

			Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
			addTableHeader(table);
			addRows(table);
			addCustomRows(table);

			doc.add(table);

			doc.close();
		}

		return doc;
	}

	private void addTableHeader(Table table) {
		Stream.of("column header 1", "column header 2", "column header 3").forEach(columnTitle -> {
			Cell header = new Cell();
			header.setBackgroundColor(ColorConstants.LIGHT_GRAY);
			header.setBorder(new SolidBorder(ColorConstants.BLACK, 2));
			header.add(new Paragraph(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(Table table) {
		for (int i = 0; i < 9; i++) {
			table.addCell("hi");
		}
	}

	private void addCustomRows(Table table) throws URISyntaxException, BadElementException, IOException {
		Path path = Paths.get(getClass().getResource("/img/userlogin.png").toURI());
		Image img = new Image(ImageDataFactory.create(path.toAbsolutePath().toString()));
		img.setWidth(UnitValue.createPercentValue(60));

		Cell imageCell = new Cell();
		imageCell.add(img);
		imageCell.setBorder(Border.NO_BORDER);
		table.addCell(imageCell);

		Cell horizontalAlignCell = new Cell();
		Paragraph par = new Paragraph("row 2, col 2");
		par.setTextAlignment(TextAlignment.CENTER);
		horizontalAlignCell.add(par);
		table.addCell(horizontalAlignCell);

		Cell verticalAlignCell = new Cell();
		verticalAlignCell.add(new Paragraph("row 2, col 3"));
		verticalAlignCell.setVerticalAlignment(VerticalAlignment.BOTTOM);
		table.addCell(verticalAlignCell);
	}

	public static void main(String[] args)
			throws MalformedURLException, IOException, URISyntaxException, DocumentException {
		new IText7PdfCreator().createDocument(new File("C:\\Users\\jose.m.prieto.villar\\Desktop\\temp3.pdf"));
	}

}
