// Product
interface DocumentReader {
    void read();
}

// Concrete Products
class PdfReader implements DocumentReader {
    public void read() { System.out.println("Reading PDF Document..."); }
}
class DocxReader implements DocumentReader {
    public void read() { System.out.println("Reading DOCX Document..."); }
}
class TxtReader implements DocumentReader {
    public void read() { System.out.println("Reading TXT Document..."); }
}

// Factory
class ReaderFactory {
    public static DocumentReader getReader(String type) {
        switch (type.toLowerCase()) {
            case "pdf": return new PdfReader();
            case "docx": return new DocxReader();
            case "txt": return new TxtReader();
            default: throw new IllegalArgumentException("Unknown file type");
        }
    }
}

public class FactoryDemo {
    public static void main(String[] args) {
        DocumentReader reader1 = ReaderFactory.getReader("pdf");
        reader1.read();

        DocumentReader reader2 = ReaderFactory.getReader("docx");
        reader2.read();

        DocumentReader reader3 = ReaderFactory.getReader("txt");
        reader3.read();
    }
}

