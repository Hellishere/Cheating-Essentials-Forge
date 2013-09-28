package common.kodehawa.ce.module.man;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import common.kodehawa.ce.logger.DynamicLogger;
import common.kodehawa.ce.module.classes.AnimalESP;
import common.kodehawa.ce.module.classes.ArrowDodge;
import common.kodehawa.ce.module.classes.AutoRespawn;
import common.kodehawa.ce.module.classes.BlockFinder;
import common.kodehawa.ce.module.classes.ChestFinder;
import common.kodehawa.ce.module.classes.CreativeFly;
import common.kodehawa.ce.module.classes.DynamicFly;
import common.kodehawa.ce.module.classes.EvtNoFall;
import common.kodehawa.ce.module.classes.FastBreak;
import common.kodehawa.ce.module.classes.FastPlace;
import common.kodehawa.ce.module.classes.Fly;
import common.kodehawa.ce.module.classes.Fullbright;
import common.kodehawa.ce.module.classes.HighJump;
import common.kodehawa.ce.module.classes.InvisiblePlayer;
import common.kodehawa.ce.module.classes.MCUtil_ReloadChunks;
import common.kodehawa.ce.module.classes.NoFall;
import common.kodehawa.ce.module.classes.NoWeb;
import common.kodehawa.ce.module.classes.Sprint;
import common.kodehawa.ce.module.classes.Step;
import common.kodehawa.ce.module.classes.WaterWalk;
import common.kodehawa.ce.module.core.ModuleAbstract;
import common.kodehawa.ce.util.Tickable;

public class ModuleManager {

	private static volatile ModuleManager instance = new ModuleManager();
	public List<ModuleAbstract> avModules = Lists.newArrayList();
	public List<Tickable> tickList = Lists.newArrayList();

	
	public ModuleManager(){
		loadClasses();
		DynamicLogger.instance().writeLog("Loaded "+avModules.size()+" modules in Cheating Essentials" , Level.INFO);
	}
	
	public void loadClasses(){
		addModule(new Fly()); addModule(new DynamicFly()); addModule(new FastPlace());
		addModule(new Fullbright()); addModule(new HighJump()); addModule(new Sprint());
		addModule(new FastBreak()); addModule(new Step()); addModule(new WaterWalk());
		addModule(new MCUtil_ReloadChunks()); addModule(new AutoRespawn()); addModule(new EvtNoFall());
		addModule(new NoFall()); addModule(new CreativeFly()); addModule(new ArrowDodge()); 
		addModule(new ChestFinder()); addModule(new InvisiblePlayer()); addModule(new NoWeb());
		addModule(new AutoRespawn()); addModule(new BlockFinder()); addModule(new AnimalESP());
	}
	
	public static void getKey(){
        for(ModuleAbstract m : instance().avModules){
            m.getKeybind();
        }
    }
	
    public ModuleAbstract getModuleClass(ModuleAbstract m){
    	for(ModuleAbstract m1 : avModules){
			if(m1.getClass().equals(m)){
				return m1;
			}
		}
		return null;
    }
    
    public void getModules(){
    	Collections.unmodifiableList(avModules);
    }
    		    
	public void addModule(ModuleAbstract m){
		avModules.add(m);
		DynamicLogger.instance().writeLog("Loaded Module: "+StringUtils.upperCase(m.getModuleName())+" in Category: "+m.cat+"" + "[Key: "+Keyboard.getKeyName(m.getKeybind())+"]", Level.INFO);
	}
	
	public void addTick(Tickable t){
		tickList.add(t);
	}
	
	public void removeTick(Tickable t){
		tickList.remove(t);
	}
	
	public static ModuleManager instance(){
		return instance;
	}
}
