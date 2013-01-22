package org.peij.tag;

/**
 * @author trgoofi
 *
 */
public class NextTag extends ClickableAwareTag {

  @Override
  protected int pageNumberToExpose() {
    return pagination.next();
  }

  @Override
  protected boolean clickable() {
    return pagination.hasNextPage();
  }

  @Override
  protected String tagName() {
    return "next";
  }
}
