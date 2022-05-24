CODEPATH='/Users/yiuminglai/GitProjects/algorithm_practice/java/out/production/algorithm_practice'

if [[ "$CLASSPATH" != *"$CODEPATH"* ]]; then
  echo "add new dir to classpath..."
  export CLASSPATH="/Users/yiuminglai/GitProjects/algorithm_practice/java/out/production/algorithm_practice:$CLASSPATH"
fi

java ctci.threadslock.ContextSwitch1
java ctci.threadslock.ContextSwitch2