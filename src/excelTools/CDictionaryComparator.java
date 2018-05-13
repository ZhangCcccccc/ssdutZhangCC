package excelTools;

import java.util.Comparator;

import net.xidlims.domain.CDictionary;

public class CDictionaryComparator implements Comparator<CDictionary> {

	@Override
	public int compare(CDictionary c1, CDictionary c2){
		if(c1.getId() > c2.getId())
		{
			return 1;
		}
		else if(c1.getId().equals(c2.getId()))
		{
			return 0;
		}
		
		return -1;
	}
}
