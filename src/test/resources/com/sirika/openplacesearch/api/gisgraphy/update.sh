#!/bin/sh

set -e
#rm fullTextSearchForParisWithFullStyle.xml
rm *.xml
wget -O fullTextSearchForParisWithFullStyle.xml 'http://services.gisgraphy.com/fulltext/fulltextsearch?q=Paris&placetype=CIty&country=&spellchecking=true&__checkbox_spellchecking=true&lang=&format=XML&style=FULL&__checkbox_indent=true&from=1&to=10'
wget -O geolocalizationNearParis.xml 'http://services.gisgraphy.com/geoloc/geolocsearch?lat=48.853408813476&lng=2.3487999439239&radius=&placetype=city&format=XML&__checkbox_indent=true&from=1&to=10'

echo "Success"
