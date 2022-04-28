## code snippet format

The code snippets is store

"console.log": {
  "Scope": "java",
  "prefix": "cl",
  "body": [
    "System.{$1}.println(\"{$2}\")",
    ""
  ]
  "description": "Log output to console"
}

## live template format: 

The code is store at location on mac:
/Users/xxx/Library/Application Support/JetBrains/IntelliJIdea2022.1/templates/algorithm.xml

<template name="filter" value="$ITERABLE_TYPE$.stream()&#10;    .filter($VAR$ -&gt; $VAR_CONDITION$)&#10;    .collect(Collectors.toList());" description="Iterate Iterable | Array in J2SDK 5.0 syntax" toReformat="true" toShortenFQNames="true">
  <variable name="ITERABLE_TYPE" expression="iterableVariable()" defaultValue="" alwaysStopAt="true" />
  <variable name="VAR" expression="suggestVariableName()" defaultValue="" alwaysStopAt="true" />
  <variable name="VAR_CONDITION" expression="suggestVariableName()" defaultValue="" alwaysStopAt="true" />
  <context>
    <option name="JAVA_CODE" value="true" />
  </context>
</template>


## Reference
1. https://github.com/benweizhu/Intellij-IDEA-Java-live-template
2. https://www.youtube.com/watch?v=TGh2NpCIDlc