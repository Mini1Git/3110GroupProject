extends Control

@onready var ui = get_parent()

func open():
	visible = true
	Input.mouse_mode = Input.MOUSE_MODE_CONFINED

func close():
	visible = false
	Input.mouse_mode = Input.MOUSE_MODE_CAPTURED

func _on_character_pressed():
	visible = false
	ui.character.open()
	ui.openMenu = "character"

func _on_talents_pressed():
	visible = false
	ui.talents.open()
	ui.openMenu = "talent"

func _on_inventory_pressed():
	visible = false
	ui.inventory.open()
	ui.openMenu = "inventory"

func _on_magic_pressed():
	visible = false
	ui.magic.open()
	ui.openMenu = "magic"

func _on_settings_pressed():
	visible = false
	ui.settings.open()
	ui.openMenu = "settings"

func _on_close_pressed():
	close()
	ui.player.unpause()
	ui.openMenu = ""
