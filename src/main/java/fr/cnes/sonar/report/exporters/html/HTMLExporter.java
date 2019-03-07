package fr.cnes.sonar.report.exporters.html;

import com.hp.gagawa.java.elements.Div;
import fr.cnes.sonar.report.exceptions.BadExportationDataTypeException;
import fr.cnes.sonar.report.exporters.IExporter;
import fr.cnes.sonar.report.model.Issue;
import fr.cnes.sonar.report.model.Report;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HTMLExporter implements IExporter {

   @Override
   public File export(Object data, String path, String filename) throws BadExportationDataTypeException, IOException, OpenXML4JException, XmlException {
      if (!(data instanceof Report)) {
         throw new BadExportationDataTypeException();
      }

      final Report report = (Report) data;
      File htmlFileReport = null;
      try (InputStream fileInputStream = getClass().getResourceAsStream("/template/template.html")) {
         String htmlString = IOUtils.toString(fileInputStream, Charset.forName("UTF-8"));
         htmlString = setTitle(htmlString, report.getProjectName());
         htmlString = setAuthor(htmlString, report.getProjectName());
         htmlString = setIssues(htmlString, exportIssuesToHTML(report.getIssues()));
         htmlFileReport = new File(path);
         FileUtils.writeStringToFile(htmlFileReport, htmlString);
      }

      return htmlFileReport;
   }

   private String setAuthor(String htmlString, String author) {
      return htmlString.replace("$author", author);
   }

   private String setTitle(String htmlString, String projectName) {
      return htmlString.replace("$title", projectName);
   }

   private String setIssues(String htmlString, String issuesHtml) {
      return htmlString.replace("$issues", issuesHtml);
   }

   private String exportIssuesToHTML(List<Issue> issueList) {
      Map<String, List<Issue>> result = issueList.stream().collect(Collectors.groupingBy(Issue::getComponent));
      return exportIssuesToHTML(result);
   }

   private String exportIssuesToHTML(Map<String, List<Issue>> issueMap) {
      Div div = new Div().setCSSClass("article");
      for (String issueClass : issueMap.keySet()) {
         div.appendChild(generateArticleHeaderWithClassName(issueClass));
         div.appendChild(generateArticleBody(issueMap.get(issueClass)));
      }
      return div.write();
   }

   private Div generateArticleHeaderWithClassName(String className) {
      Div div = new Div().setCSSClass("article_header");
      div.appendText(className);
      return div;
   }

   private Div generateArticleBody(List<Issue> issueList) {
      Div div = new Div().setCSSClass("article_body");
      for (Issue issue : issueList) {
         Div divItem = new Div().setCSSClass("article_body_item");
         divItem.appendChild(generateItemDiv("article_item_line", "Line", issue.getLine()));
         divItem.appendChild(generateItemDiv("article_item_priority", "Priority", issue.getSeverity()));
         divItem.appendChild(generateItemDiv("article_item_type", "Type", issue.getType()));
         divItem.appendChild(generateItemDiv("article_item_message", "Message", issue.getMessage()));

         div.appendChild(divItem);
      }

      return div;
   }

   private Div generateItemDiv(String divClass, String divCapture, String divContent) {
      Div itemDiv = new Div().setCSSClass(divClass);
      Div titleDiv = new Div().setCSSClass("article_item_title");
      titleDiv.appendText(divCapture);
      Div lineNumber = new Div();
      lineNumber.appendText(divContent);
      itemDiv.appendChild(titleDiv).appendChild(lineNumber);
      return itemDiv;
   }
}
