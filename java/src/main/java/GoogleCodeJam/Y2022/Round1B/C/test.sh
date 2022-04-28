CODEPATH='/Users/yiuminglai/GitProjects/algorithm_practice/java/out/production/algorithm_practice'

if [[ "$CLASSPATH" != *"$CODEPATH"* ]]; then
  echo "add new dir to classpath..."
  export CLASSPATH="/Users/yiuminglai/GitProjects/algorithm_practice/java/out/production/algorithm_practice:$CLASSPATH"
fi

python interactive_runner.py python3 local_testing_tool.py3 0 -- java GoogleCodeJam.Y2022.Round1B.C.Solution
