package org.peij.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author trgoofi
 */
public abstract class PageTagSupport extends SimpleTagSupport {
  private Object backup;

  protected int pages;
  protected int window = 10;
  protected int current;
  protected String page;


  @Override
  public void doTag() throws JspException, IOException {
    if (pages < 1 || current < 1 || pages < current) return;

    backupPotentialCollidedPageVariable();
    doBody();
    restorePotentialCollidedPageVariable();
  }


  public abstract void doBody() throws JspException, IOException;

  protected WindowRange windowRange() {
    int start, end;
    int middle = window / 2;

    if (current <= middle) {
      start = 1;
      end = window;
    } else if (current + middle > pages) {
      start = pages - (window - 1);
      end = pages;
    } else {
      start = current - middle;
      end = current + middle;
      if (window % 2 == 0) {
        --end; // Even case. Minus one to prevent exceed the window.
      }
    }

    start = Math.max(start, 1);
    end = Math.min(end, pages);

    return new WindowRange(start, end);
  }

  protected int previous() {
    return current - 1;
  }

  protected int next() {
    return current + 1;
  }

  protected int first() {
    return 1;
  }

  protected int last() {
    return pages;
  }

  protected boolean isFirstPage() {
    return current == 1;
  }

  protected boolean isLastPage() {
    return current == pages;
  }

  protected boolean hasPreviousPage() {
    return current > 1;
  }

  protected boolean hasNextPage() {
    return current < pages;
  }


  protected static class WindowRange {
    private int start;
    private int end;

    private WindowRange(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }
  }

  protected void exposePageNumber(int page) {
    if (this.page != null) {
      JspContext jspContext = getJspContext();
      jspContext.setAttribute(this.page, page);
    }
  }

  private void restorePotentialCollidedPageVariable() {
    if (page != null) {
      getJspContext().setAttribute(page, backup, PageContext.PAGE_SCOPE);
    }
  }

  private void backupPotentialCollidedPageVariable() {
    if (page != null) {
      backup = getJspContext().getAttribute(page, PageContext.PAGE_SCOPE);
    }
  }


  public void setPages(int pages) {
    check(pages < 0, "pages must not less than zero!");
    this.pages = pages;
  }

  public void setWindow(int window) {
    check(window < 1, "window must be positive!");
    this.window = window;
  }

  public void setCurrent(int current) {
    check(current < 0, "current must not less than zero!");
    this.current = current;
  }

  public void setPage(String page) {
    this.page = page;
  }

  private void check(boolean assertion, String message) {
    if (assertion) {
      throw new IllegalArgumentException(message);
    }
  }
}
