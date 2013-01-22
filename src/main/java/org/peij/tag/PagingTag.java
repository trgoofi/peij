package org.peij.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author trgoofi
 */
public class PagingTag extends PageTagSupport {
  private JspFragment url;

  private String pagingClass = "pagination";
  private String activeClass = "active";
  private String disabledClass = "disabled";

  private boolean enableEllipsis = true;
  private boolean enableFirstLast = false;


  @Override
  public void doBody() throws JspException, IOException {
    PagingMaker pagingMaker = new PagingMaker();

    if (enableFirstLast) {
      pagingMaker.add(getMessage("first"), !isFirstPage(), disabledClass, first());
    }

    pagingMaker.add(getMessage("previous"), hasPreviousPage(), disabledClass, previous());

    WindowRange windowRange = windowRange();
    for (int i = windowRange.getStart(); i <= windowRange.getEnd(); i++) {
      pagingMaker.add(Integer.toString(i), (i != current), activeClass, i);
    }

    if (enableEllipsis && windowRange.getEnd() < pages) {
      pagingMaker.add("...", false, disabledClass, PagingMaker.PAGE_NUMBER_PADDING);
    }

    pagingMaker.add(getMessage("next"), hasNextPage(), disabledClass, next());

    if (enableFirstLast) {
      pagingMaker.add(getMessage("last"), !isLastPage(), disabledClass, last());
    }

    getJspContext().getOut().write(pagingMaker.make());
  }


  private String getMessage(String message) {
    Locale locale = Locale.getDefault();
    JspContext jspContext = getJspContext();
    if (jspContext instanceof PageContext) {
      locale = ((PageContext) jspContext).getRequest().getLocale();
    }

    return ResourceBundle.getBundle("i18n/message", locale).getString(message);
  }

  private String resolveUrl() throws IOException, JspException {
    if (url != null) {
      StringWriter sw = new StringWriter();
      url.invoke(sw);
      return sw.toString();
    }
    return "";
  }


  private class PagingMaker {
    static final int PAGE_NUMBER_PADDING = -1;
    StringBuilder sb = new StringBuilder();

    PagingMaker() {
      sb.append("<ul class=\"").append(pagingClass).append("\">");
    }

    void add(String text, boolean clickable, String cssClass, int pageNumber) throws IOException, JspException {
      if (clickable) {
        exposePageNumber(pageNumber);
        sb.append("<li><a href=\"").append(resolveUrl()).append("\">").append(text).append("</a></li>");
      } else {
        sb.append("<li class=\"").append(cssClass).append("\"><a>").append(text).append("</a></li>");
      }
    }

    String make() {
      return sb.append("</ul>").toString();
    }
  }


  public void setUrl(JspFragment url) {
    this.url = url;
  }

  public void setPagingClass(String pagingClass) {
    this.pagingClass = pagingClass;
  }

  public void setDisabledClass(String disabledClass) {
    this.disabledClass = disabledClass;
  }

  public void setActiveClass(String activeClass) {
    this.activeClass = activeClass;
  }

  public void setEnableEllipsis(boolean enableEllipsis) {
    this.enableEllipsis = enableEllipsis;
  }

  public void setEnableFirstLast(boolean enableFirstLast) {
    this.enableFirstLast = enableFirstLast;
  }
}
