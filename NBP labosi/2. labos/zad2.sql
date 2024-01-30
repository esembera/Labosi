select w.name,round(sum(st_length(w.geom))) as len, to_char(round(((sum(st_length(st_intersection(w.geom, s.geom))))/sum(st_length(w.geom)))*100),'999%') as len_in_croatia
from waterways as w, state as s
where w.fclass = 'river'
group by w.name
order by len desc 