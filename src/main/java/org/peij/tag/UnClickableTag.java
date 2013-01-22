package org.peij.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author trgoofi
 */
public class UnClickableTag extends SimpleTagSupport {

  @Override
  public void doTag() throws JspException, IOException {
    ClickableAwareTag clickable = (ClickableAwareTag) findAncestorWithClass(this, ClickableAwareTag.class);
    if (clickable == null) {
      throw new JspTagException("unclickable tag must inside fist, previous, number ... tag.");
    }

    if (!clickable.clickable()) {
      getJspBody().invoke(null);
    }

  }

}
