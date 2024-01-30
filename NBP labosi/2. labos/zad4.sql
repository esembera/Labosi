create table forestAreas
as
SELECT
	zup.name_1 as name,
	ST_Area(zup.geom) as totalArea,
	SUM(ST_Area(ST_intersection(land.geom, zup.geom))) as forestArea,
	to_char(round((SUM(ST_Area(ST_intersection(land.geom, zup.geom)))) / (ST_Area(zup.geom))*100),'999%') as forestPercentage,
	zup.geom
FROM
	landuse as land, 
	county as zup
WHERE
	land.fclass = 'forest'
GROUP BY
	zup.gid 
