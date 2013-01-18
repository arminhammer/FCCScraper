/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author armin
 */
public class FCCClient {

    private static void printMenu() {
        System.out.println("Choose one of the following options:");
        System.out.println("(a) Run a scrape");
        System.out.println("(b) Run a search");
        System.out.println("(c) View System Report");
        System.out.println("(d) Run a search on a sample doc");
        System.out.println("(e) Run a numerical search on a sample doc");
        System.out.println("(q) Quit");
    }

    private static Datastore getConnection() {
        Datastore ds;
        try {
            Mongo m = new Mongo("localhost", 27017);
            ds = new Morphia().map(FCCProceeding.class).createDatastore(m, "fcc");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing mongo db.");
        }
        return ds;
    }

    private static List<String> generatePatterns(String query) {
        ArrayList<String> patterns = new ArrayList<String>();
        patterns.add(query);
        patterns.add(query + " %");
        patterns.add(query + "-percent");
        patterns.add(query + " MHz");
        patterns.add(query + "-" + query + "MHz");
        patterns.add(query + " percent");
        patterns.add(query + " million");
        patterns.add(query + " GHz");
        patterns.add(query + " dBm");
        patterns.add(query + " dB");
        patterns.add(query + " dBi");
        patterns.add(query + " meters");
        patterns.add(query + " kilometers");
        patterns.add(query + "-km");
        patterns.add(query + " foot");
        patterns.add(query + " % increase");
        patterns.add(query + " % decrease");
        patterns.add(query + "/" + query + " MHz");
        patterns.add(query + "/" + query + " GHz");
        patterns.add(query + " times");
        patterns.add(query + " devices");
        patterns.add(query + " dB increase");
        patterns.add(query + " dB decrease");
        patterns.add(query + " milliseconds");
        patterns.add(query + " kilobytes");
        patterns.add(query + " times");
        patterns.add(query + " mbps");
        patterns.add(query + " Mbps");
        patterns.add(query + " kbps");
        patterns.add(query + " Kbps");
        patterns.add(query + " dBm/MHz");
        patterns.add(query + " watt");
        patterns.add(query + " watts");
        patterns.add(query + " W");
        patterns.add(query + " kW");
        patterns.add(query + " EIRP");
        patterns.add(query + " degrees");
        patterns.add(query + " billion");
        patterns.add(query + " bits");
        patterns.add(query + " K");
        patterns.add(query + " miles");
        patterns.add(query + "-meter");
        patterns.add(query + " devices");
        patterns.add(query + " handsets");
        patterns.add(query + " towers");
        patterns.add(query + " receivers");
        patterns.add("part " + query);
        patterns.add("Part " + query);
        patterns.add(query + " CFR " + query);
        patterns.add(query + " C.F.R. " + query);
        return patterns;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the FCC Scraper/Analysis text client!");
        String choice = "";
        while (!choice.equals("q")) {
            printMenu();
            choice = in.next();
            if (choice.equals("a")) {
                System.out.println("Not supported yet.");
            } else if (choice.equals("b")) {
                System.out.println("Please type in the query you would like to search:");
                String query = in.next();
                Datastore ds = getConnection();
                List<FCCProceeding> list = ds.createQuery(FCCProceeding.class).asList();
                for (FCCProceeding proc : list) {
                    List<Filing> filings = proc.getFilings();
                    for (Filing filing : filings) {
                        for (String doc : filing.getDocuments()) {
                            String location = "/home/armin/data/scraper/" + proc.getProceeding() + "/" + doc;
                            try {
                                PDDocument pdf = PDDocument.load(location);
                                PDFTextStripper stripper = new PDFTextStripper();
                                String text = stripper.getText(pdf);
                                System.out.println("Printing text for " + doc);
                                System.out.println(text.substring(0, 200));
                                pdf.close();
                            } catch (IOException ex) {
                                Logger.getLogger(FCCClient.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            } else if (choice.equals("c")) {
                Datastore ds = getConnection();
                List<FCCProceeding> list = ds.createQuery(FCCProceeding.class).asList();
                int count = 0;
                for (FCCProceeding proceeding : list) {
                    System.out.println(proceeding.getProceeding());
                    count++;
                }
                System.out.println("There are currently " + count + " proceedings.c");
            } else if (choice.equals("d")) {
                System.out.println("Please type in the query you would like to search:");
                String query = in.next();
                List<String> patterns = generatePatterns(query);
                Datastore ds = getConnection();
                FCCProceeding proceeding = ds.createQuery(FCCProceeding.class).field("proceeding").equal("RM-7902").get();
                List<Filing> filings = proceeding.getFilings();
                Filing targetFile = null;
                for (Filing filing : filings) {
                    if (filing.getFilingId().equals("?id=101197")) {
                        targetFile = filing;
                        break;
                    }
                }
                String location = "/home/armin/data/scraper/" + proceeding.getProceeding() + "/" + targetFile.getDocuments().get(0);
                try {
                    PDDocument pdf = PDDocument.load(location);
                    PDFTextStripper stripper = new PDFTextStripper();
                    String text = stripper.getText(pdf);
                    System.out.println("Printing text for " + targetFile.getDocuments().get(0));
                    System.out.println(text);
                    for (String pattern : patterns) {
                        System.out.println("Searching for " + pattern);
                        proceeding.removeHits(ds, query, pattern);
                        //ds.delete(ds.createQuery(Filing.class).field("filingId").equal("?id=101197").get());
                        Pattern p = Pattern.compile(pattern);
                        Matcher matcher = p.matcher(text);
                        int count = 0;
                        while (matcher.find()) {
                            count++;
                            Hit hit = new Hit();
                            hit.setGenericPattern(query);
                            hit.setActualPattern(pattern);
                        }
                        System.out.println("Found " + count + " hits for " + pattern);
                    }
                    pdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(FCCClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (choice.equals("q")) {
                System.exit(0);
            } else {
                System.out.println("Could not process the command.");
            }
        }
        System.out.println(choice);
    }
}
