	.section	__TEXT,__text,regular,pure_instructions
	.macosx_version_min 10, 12
	.globl	_foo
	.align	4, 0x90
_foo:                                   ## @foo
	pushq	%rbp
	movq	%rsp, %rbp
	movl	%edi, -4(%rbp)
	cmpl	$2, -4(%rbp)
	retq

_main:                                  ## @main
	subq	$16, %rsp
	movl	$2, %edi
	movl	$0, -4(%rbp)
	callq	_foo
	xorl	%eax, %eax
	addq	$16, %rsp
	popq	%rbp
	retq

.subsections_via_symbols
