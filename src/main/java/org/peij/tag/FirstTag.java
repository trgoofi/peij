package org.peij.tag;

/**
 * @author trgoofi
 *
 */
public class FirstTag extends ClickableAwareTag {

  @Override
  protected int pageNumberToExpose() {
    return pagination.first();
  }

  @Override
  protected boolean clickable() {
    return !pagination.isFirstPage();
  }

  @Override
  protected String tagName() {
    return "fist";
  }
}
