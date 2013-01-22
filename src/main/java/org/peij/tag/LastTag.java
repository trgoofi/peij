package org.peij.tag;

/**
 * @author trgoofi
 * 
 */
public class LastTag extends ClickableAwareTag {

  @Override
  protected int pageNumberToExpose() {
    return pagination.last();
  }

  @Override
  protected boolean clickable() {
    return !pagination.isLastPage();
  }

  @Override
  protected String tagName() {
    return "last";
  }
}
