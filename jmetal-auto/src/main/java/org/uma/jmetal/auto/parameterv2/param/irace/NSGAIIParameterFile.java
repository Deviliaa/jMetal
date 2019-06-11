package org.uma.jmetal.auto.parameterv2.param.irace;

import org.uma.jmetal.auto.parameterv2.param.CategoricalParameter;
import org.uma.jmetal.auto.parameterv2.param.OrdinalParameter;
import org.uma.jmetal.auto.parameterv2.param.Parameter;
import org.uma.jmetal.auto.parameterv2.param.catalogue.*;

import java.util.Map;

public class NSGAIIParameterFile {
  public void create(Map<String, Parameter<?>> parameterMap) {
    parameterMap.forEach((key, value) -> System.out.println("Name: " + key + ". Value: " + value));

    AlgorithmResult algorithmResult = (AlgorithmResult) parameterMap.get("algorithmResult");
    PopulationSize populationSize = (PopulationSize) parameterMap.get("populationSize");
    PopulationSizeWithArchive populationSizeWithArchive =
        (PopulationSizeWithArchive) parameterMap.get("populationSizeWithArchive");
    OffspringPopulationSize offspringPopulationSize =
        (OffspringPopulationSize) parameterMap.get("offspringPopulationSize");
    CreateInitialSolutions createInitialSolutions =
        (CreateInitialSolutions) parameterMap.get("createInitialSolutions");
    Variation variation = (Variation) parameterMap.get("variation") ;
    Selection selection = (Selection) parameterMap.get("selection") ;

    String formatString = "%-40s %-40s %-7s %-30s %-20s\n";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(
        String.format(
            formatString,
            algorithmResult.getName(),
            "--" + algorithmResult.getName(),
            decodeType(algorithmResult),
            decodeValidValues(algorithmResult),
            ""));

    stringBuilder.append(
        String.format(
            formatString,
            populationSize.getName(),
            "--" + populationSize.getName(),
            decodeType(populationSize),
            decodeValidValues(populationSize),
            "| algorithmResult %in% c(\"population\")"));

    stringBuilder.append(
        String.format(
            formatString,
            populationSizeWithArchive.getName(),
            "--" + populationSizeWithArchive.getName(),
            decodeType(populationSizeWithArchive),
            decodeValidValues(populationSizeWithArchive),
            "| algorithmResult %in% c(\"externalArchive\")"));

    stringBuilder.append(
        String.format(
            formatString,
            offspringPopulationSize.getName(),
            "--" + offspringPopulationSize.getName(),
            decodeType(offspringPopulationSize),
            decodeValidValues(offspringPopulationSize),
            ""));

    stringBuilder.append("#\n") ;

    stringBuilder.append(
        String.format(
            formatString,
            createInitialSolutions.getName(),
            "--" + createInitialSolutions.getName(),
            decodeType(createInitialSolutions),
            decodeValidValues(createInitialSolutions),
            ""));

    stringBuilder.append("#\n") ;

    stringBuilder.append(
        String.format(
            formatString,
            variation.getName(),
            "--" + variation.getName(),
            decodeType(variation),
            decodeValidValues(variation),
            ""));

    stringBuilder.append("#\n") ;

    stringBuilder.append(
        String.format(
            formatString,
            selection.getName(),
            "--" + selection.getName(),
            decodeType(selection),
            decodeValidValues(selection),
            ""));

    System.out.println(stringBuilder.toString());
  }

  private String decodeType(Parameter<?> parameter) {
    String result = " ";
    if (parameter instanceof CategoricalParameter) {
      result = "c";
    } else if (parameter instanceof OrdinalParameter) {
      result = "o";
    } else if (parameter instanceof Parameter) {
      result = "o";
    }

    return result;
  }

  private String decodeValidValues(Parameter<?> parameter) {
    String result = " ";

    if (parameter instanceof CategoricalParameter) {
      result = ((CategoricalParameter<?>) parameter).getValidValues().toString();
      result = result.replace("[", "(");
      result = result.replace("]", ")");
    } else if (parameter instanceof OrdinalParameter) {
      result = ((OrdinalParameter<?>) parameter).getValidValues().toString();
      result = result.replace("[", "(");
      result = result.replace("]", ")");
    } else if (parameter instanceof Parameter) {
      result = "(" + parameter.getValue() + ")";
    }

    return result;
  }
}