if [ -f .env ]; then
  set -o allexport
  source .env
  set +o allexport
fi

grails clean
grails run-app