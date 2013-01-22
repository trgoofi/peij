package org.peij.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * @author trgoofi
 * 
 */
public class NumberTag extends ClickableAwareTag {
  private int pageNumber;
  private boolean clickable;
  
  @Override
  public void doBody() throws JspException, IOException {
    PageTagSupport.WindowRange windowRange = pagination.windowRange();

    for (int i = windowRange.getStart(); i <= windowRange.getEnd(); i++) {
      pageNumber = i;
      clickable = (pagination.current != i);
      pagination.exposePageNumber(pageNumberToExpose());
      getJspBody().invoke(null);
    }
  }

  @Override
  protected int pageNumberToExpose() {
    return pageNumber;
  }

  @Override
  protected boolean clickable() {
    return clickable;
  }

  @Override
  protected String tagName() {
    return "number";
  }
}
