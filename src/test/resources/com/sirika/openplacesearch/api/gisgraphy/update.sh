#!/bin/sh

rm fullTextSearchForParisWithFullStyle.xml
wget -O fullTextSearchForParisWithFullStyle.xml 'http://services.gisgraphy.com/fulltext/fulltextsearch?q=Paris&placetype=CIty&country=&spellchecking=true&__checkbox_spellchecking=true&lang=&format=XML&style=FULL&__checkbox_indent=true&from=1&to=10'
