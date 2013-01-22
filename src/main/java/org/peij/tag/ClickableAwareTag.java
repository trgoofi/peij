package org.peij.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author trgoofi
 */
public abstract class ClickableAwareTag extends SimpleTagSupport {
  protected PaginationTag pagination;

  @Override
  public void doTag() throws JspException, IOException {
    pagination = (PaginationTag) findAncestorWithClass(this, PaginationTag.class);
    if (pagination == null) {
      throw new JspException(tagName() + " tag must inside pagination tag");
    }

    doBody();
  }


  protected void doBody() throws JspException, IOException {
    pagination.exposePageNumber(pageNumberToExpose());
    getJspBody().invoke(null);
  }


  protected abstract int pageNumberToExpose();

  protected abstract boolean clickable();

  protected abstract String tagName();

}
