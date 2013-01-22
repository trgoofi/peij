package org.peij.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * @author trgoofi
 */
public class EllipsisTag extends ClickableAwareTag {

  @Override
  protected void doBody() throws JspException, IOException {
    PageTagSupport.WindowRange windowRange = pagination.windowRange();
    if (windowRange.getEnd() < pagination.pages) {
      getJspBody().invoke(null);
    }
  }

  @Override
  protected int pageNumberToExpose() {
    return 0;
  }

  @Override
  protected String tagName() {
    return "ellipsis";
  }

  @Override
  public boolean clickable() {
    return false;
  }
}
