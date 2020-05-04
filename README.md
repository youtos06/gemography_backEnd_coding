# gitrepos
Gemography back end test

## Rest

### /gitTrend

* "https://api.github.com/search/repositories?q=created:>{date}&sort=stars&order=desc" url for github api bring <b>29</b> repository for each page
* the call for the api should be done at least 4 times to retrieve the 100 most famous repos for the last 30 days but since its the same point i only used the first 3 page
* i used a database stockage (H2) to minimize the call between the server and the git api, it only need the first call to stock the data and at the start of each new day the old data is removed
* creation of service to handle the complexity , which provide the possibility of changing he service layer without changing the Controller
a special case would be working in international field where one country will have a date and the other can be a hear or behind by one day , thats why the choice of the date was based on the server date.


### /gitTrend/{language}

*  creation of Dto class adapted to the new mapping of data converted by the call of the gitfalousrepos service
*  if the url is called before the first the date of the famous repos will be stocked so that it will be used in other calls


## architecture

* model ( include the dto model)
* dao 
* consumingRest ( consuming the git api)
* rest ( including the rest controller)
* service

## a question : is the url verbs fellowing the rest approach.

the simple answer is that the verbs ain't fellowing the rest approach, and the project might lack some of the objectives

