package org.umuc.swen.capstone.brewer.model.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColorScale;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerScaleColorChooserUtil {

  private static final String colorBrewerJsonFilePath = "colorbrewer.json";
  private static List<BrewerColorScale> brewerColorScales;
  private static Map<String, BrewerColorScale> brewerColorScaleMap;

  /**
   * Reads file only once and returns the {@link BrewerColorScale} from the first time the json file was read
   *
   * @return {@link List} of {@link BrewerColorScale}
   */
  public List<BrewerColorScale> getBrewerColorScale() {
    return getColorBrewerScaleFromFile();
  }

  /**
   * Converts the {@link List} of {@link BrewerColorScale} to a {@link Map} for easier manipulation
   *
   * @return {@link Map<String, BrewerColorScale>}
   */
  public Map<String, BrewerColorScale> getBrewerColorScaleMap() {
    if (Objects.isNull(brewerColorScaleMap)) {
      brewerColorScaleMap = getBrewerColorScale()
              .stream()
              .collect(Collectors.toMap(BrewerColorScale::getName, Function.identity()));
    }
    return brewerColorScaleMap;
  }

  private List<BrewerColorScale> getColorBrewerScaleFromFile() {

    if (Objects.isNull(brewerColorScales)) {
      try {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(colorBrewerJsonFilePath);
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        brewerColorScales = new Gson().fromJson(reader, new TypeToken<ArrayList<BrewerColorScale>>(){}.getType());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return brewerColorScales;
  }
}
