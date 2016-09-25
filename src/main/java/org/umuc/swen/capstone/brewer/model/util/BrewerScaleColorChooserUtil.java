package org.umuc.swen.capstone.brewer.model.util;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColorScale;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerScaleColorChooserUtil {

  private static final String colorBrewerJsonFilePath = "colorbrewer.json";
  private static BrewerColorScale brewerColorScale;

  /**
   * Reads file only once and returns the {@link BrewerColorScale} from the first time the json file was read
   *
   * @return {@link BrewerColorScale}
   */
  public BrewerColorScale getBrewerColorScale() {
    return getColorBrewerScaleFromFile();
  }

  private BrewerColorScale getColorBrewerScaleFromFile() {

    if (Objects.isNull(brewerColorScale)) {
      try {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(colorBrewerJsonFilePath);
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        brewerColorScale = new Gson().fromJson(reader, BrewerColorScale.class);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return brewerColorScale;
  }
}
