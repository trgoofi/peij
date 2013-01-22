package org.peij.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * @author trgoofi
 *
 */
public class PaginationTag extends PageTagSupport {

  @Override
  public void doBody() throws JspException, IOException {
    getJspBody().invoke(null);
  }

}
